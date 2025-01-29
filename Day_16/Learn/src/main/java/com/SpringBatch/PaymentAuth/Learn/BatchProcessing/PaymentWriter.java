package com.SpringBatch.PaymentAuth.Learn.BatchProcessing;

import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import com.SpringBatch.PaymentAuth.Learn.Repository.PaymentRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PaymentWriter implements ItemWriter<Payment> {
    private final PaymentRepository paymentRepository;

    public PaymentWriter(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
     @Transactional(propagation = Propagation.REQUIRED)
    public void write(Chunk<? extends Payment> payments) throws Exception
    {
        System.out.println("in writer");
        paymentRepository.saveAll(payments.getItems());
    }
}
