package com.mapper.practice.Controller;

import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.DTO.LoginRequest;
import com.mapper.practice.DTO.LoginResponse;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import com.mapper.practice.Service.PayloadService;
import com.mapper.practice.jwt.JwtUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController()
@CrossOrigin
public class UserController {
    private final PayloadService payloadService;
    private final JobLauncher jobLauncher;
    private final Job paymentJob;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    public UserController(PayloadService payloadService, JobLauncher jobLauncher, Job paymentJob, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.payloadService = payloadService;
        this.jobLauncher = jobLauncher;
        this.paymentJob = paymentJob;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/payments/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse response = new LoginResponse( jwtToken,userDetails.getUsername(), roles);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/convert")
    public ResponseEntity<String> convertToInternal(@RequestBody List<ExternalDto> payloadExternalDTO) {
//        InternalDto paymentInternalDTO = payloadService.convertToInternal(payloadExternalDTO);
        payloadService.setPaymentsToProcess(payloadExternalDTO);
        try {

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(paymentJob, jobParameters);
            return ResponseEntity.ok("Payment processing started successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Batch processing failed: " + e.getMessage());
        }


//        return ResponseEntity.ok(paymentInternalDTO);
    }
    @GetMapping("/successful")
    public List<SuccessfulPayloadEntity> successfulPayments() {

        return payloadService.getSuccessfulPayments();
    }
    @GetMapping("/failed")
    public List<UnSuccessfulPayloadEntity> unSuccessfulPayments() {
        return payloadService.getUnSuccessfulPayments();
    }
    @DeleteMapping("/failedpayments/{id}")
    public void deleteFRecord(@PathVariable long id)
    {
        payloadService.deleteUnTransactionById(id);
    }
    @DeleteMapping("/successfulpayments/{id}")
    public void deleteSRecord(@PathVariable long id)
    {
        payloadService.deleteSuTransactionById(id);
    }
}
