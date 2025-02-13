package com.mapper.practice.Controller;

import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import com.mapper.practice.Service.PayloadService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/payments")
public class UserController {
    private final PayloadService payloadService;
    private final JobLauncher jobLauncher;
    private final Job paymentJob;

    public UserController(PayloadService payloadService, JobLauncher jobLauncher, Job paymentJob) {
        this.payloadService = payloadService;
        this.jobLauncher = jobLauncher;
        this.paymentJob = paymentJob;
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
