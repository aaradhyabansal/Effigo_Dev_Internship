package com.SpringBatch.PaymentAuth.Learn.Controller;

import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import com.SpringBatch.PaymentAuth.Learn.Service.PaymentService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@CrossOrigin
public class PaymentController {
    private final JobLauncher jobLauncher;
    private final PaymentService paymentService;
    private final Job paymentJob;

    public PaymentController(PaymentService paymentService, JobLauncher jobLauncher, Job paymentJob) {
        this.paymentService = paymentService;
        this.jobLauncher = jobLauncher;
        this.paymentJob = paymentJob;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody List<Payment> payments) {
        try {
            paymentService.setPaymentsForProcessing(payments);

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(paymentJob, jobParameters);
            return ResponseEntity.ok("Payment processing started successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Batch processing failed: " + e.getMessage());
        }
    }

    @GetMapping("/getallpayments")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable("id") long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Payment deleted");
    }
}