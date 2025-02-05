package com.SpringBatch.PaymentAuth.Learn.Service;

import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import com.SpringBatch.PaymentAuth.Learn.Repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private List<Payment> paymentsToProcess = new ArrayList<>();
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void setPaymentsForProcessing(List<Payment> payments) {
        this.paymentsToProcess = payments;
    }

    public List<Payment> getPaymentsForProcessing() {
        return paymentsToProcess;
    }



    public ResponseEntity<String> createPayment(List<Payment> payments) {
        paymentRepository.saveAll(payments);
        return ResponseEntity.ok("Payment created successfully");
    }
    public ResponseEntity<String> deletePayment(long id) {
        paymentRepository.deleteById(id);
        return ResponseEntity.ok("Payment deleted successfully");
    }
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
