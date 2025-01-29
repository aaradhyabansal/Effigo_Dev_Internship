package com.SpringBatch.PaymentAuth.Learn.Service;

import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import com.SpringBatch.PaymentAuth.Learn.Repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public ResponseEntity<String> createPayment(List<Payment> payments) {
        paymentRepository.saveAll(payments);
        return ResponseEntity.ok("Payment created successfully");
    }
    public ResponseEntity<String> deletePayment(long id) {
        paymentRepository.deleteById(id);
        return ResponseEntity.ok("Payment deleted successfully");
    }
}
