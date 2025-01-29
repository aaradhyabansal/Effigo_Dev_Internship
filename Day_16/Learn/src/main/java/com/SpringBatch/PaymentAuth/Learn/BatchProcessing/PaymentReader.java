package com.SpringBatch.PaymentAuth.Learn.BatchProcessing;


import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentReader {

    @Bean
    public JpaPagingItemReader<Payment> paymentItemReader(EntityManagerFactory emf) {
        JpaPagingItemReader<Payment> reader = new JpaPagingItemReaderBuilder<Payment>()
                .name("paymentItemReader")
                .entityManagerFactory(emf)
                .queryString("SELECT p FROM Payment p WHERE p.status = 'PENDING'")
                .pageSize(1)
                .build();
        System.out.println(reader);
        return reader;
    }
}
