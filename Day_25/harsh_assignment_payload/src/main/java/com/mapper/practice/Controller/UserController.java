package com.mapper.practice.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.l1l2Integration.payload.DTO.ExternalDto;
import com.l1l2Integration.payload.DTO.ResultDto;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import com.mapper.practice.Service.PayloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/payments")
@Slf4j
public class UserController {
    private final PayloadService payloadService;
    private final JobLauncher jobLauncher;
    private final Job paymentJob;
    private static final String FILE_PATH = "src/main/resources/payload.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public UserController(PayloadService payloadService, JobLauncher jobLauncher, Job paymentJob) {
        this.payloadService = payloadService;
        this.jobLauncher = jobLauncher;
        this.paymentJob = paymentJob;
    }

    private List<ExternalDto> readPayloadsFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, new TypeReference<List<ExternalDto>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writePayloadsToFile(List<ExternalDto> products) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), products);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping("/convert")
    public ResponseEntity<String> convertToInternal(@RequestBody List<ExternalDto> payloadExternalDTO) {
//        InternalDto paymentInternalDTO = payloadService.convertToInternal(payloadExternalDTO);
        List<ExternalDto> payloads = readPayloadsFromFile();
        log.info("payloads {}",payloads);
        payloads.addAll(payloadExternalDTO);
        System.out.println(payloads);
        writePayloadsToFile(payloads);
//        payloadService.setPaymentsToProcess(payloadExternalDTO);
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
    public List<ResultDto> successfulPayments() {

        return payloadService.getSuccessfulPayments();
    }
    @GetMapping("/failed")
    public List<ResultDto> unSuccessfulPayments() {

        System.out.println(payloadService.getUnSuccessfulPayments());
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
