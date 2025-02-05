package com.SpringBatch.PaymentAuth.Learn.BatchProcessingConfig;

import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import java.util.List;

public class PaymentContext {
    private static ThreadLocal<List<Payment>> payments = new ThreadLocal<>();

    public static void setPayments(List<Payment> paymentList) {
        payments.set(paymentList);
    }

    public static List<Payment> getPayments() {
        return payments.get();
    }

    public static void clear() {
        payments.remove();
    }
}