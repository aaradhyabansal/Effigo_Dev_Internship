package com.SpringBatch.PaymentAuth.Learn.BatchProcessing;

import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor implements ItemProcessor <Payment, Payment> {
    @Override
    public Payment process(Payment payment) throws Exception {
        Payment processedPayment = new Payment();
        processedPayment.setId(payment.getId());
        processedPayment.setPaymentStatus("PROCESSED");
        System.out.println("in processor");
        return processedPayment;
    }

}
