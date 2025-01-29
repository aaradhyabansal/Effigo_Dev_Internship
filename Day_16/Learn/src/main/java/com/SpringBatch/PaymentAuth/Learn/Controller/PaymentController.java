package com.SpringBatch.PaymentAuth.Learn.Controller;


import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import com.SpringBatch.PaymentAuth.Learn.Service.PaymentService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final JobLauncher jobLauncher;
    private PaymentService paymentService;
    private Job paymentJob;

    public PaymentController(PaymentService paymentService, JobLauncher jobLauncher,Job paymentJob) {
        this.paymentService = paymentService;
        this.jobLauncher = jobLauncher;
        this.paymentJob=paymentJob;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody List<Payment> payments) {
        try
        {
            System.out.println("0");

            paymentService.createPayment(payments);
            System.out.println("1");



            JobParameters jobParameters=new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            System.out.println("2");

            jobLauncher.run(paymentJob,jobParameters);
            System.out.println("3");
            return ResponseEntity.ok("Payment processing started successfully");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Batch processing failed Sorry: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable("id") long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Payment deleted successfully");
    }
}
