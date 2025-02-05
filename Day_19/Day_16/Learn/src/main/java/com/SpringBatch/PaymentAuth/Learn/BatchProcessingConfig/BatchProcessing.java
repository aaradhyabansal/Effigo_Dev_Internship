package com.SpringBatch.PaymentAuth.Learn.BatchProcessingConfig;

import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import com.SpringBatch.PaymentAuth.Learn.Repository.PaymentRepository;
import com.SpringBatch.PaymentAuth.Learn.Service.PaymentService;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;


@Configuration
@Slf4j
@EnableBatchProcessing
public class BatchProcessing {

    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;


    public BatchProcessing(PaymentRepository paymentRepository, PaymentService paymentService) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }


    @Component
    public class PaymentReader implements ItemReader<Payment> {

        private Iterator<Payment> paymentIterator;


        @Override
        public Payment read() {
            if (paymentIterator == null || !paymentIterator.hasNext()) {
                List<Payment> payments = paymentService.getPaymentsForProcessing();
                if (payments.isEmpty()) {
                    return null;
                }
                this.paymentIterator = payments.iterator();
            }
            return paymentIterator.hasNext() ? paymentIterator.next() : null;
        }
    }



    @Bean
    public ItemProcessor<Payment, Payment> paymentProcessor() {
        return payment -> {
            try {
                payment.setPayment_status("PROCESSED");
                System.out.println("Processing payment: " + payment.getId());
                log.info("al payments values defined : {}",payment);
                return payment;
            } catch (Exception e) {
                System.err.println("Error processing payment: " + e.getMessage());
                return null;
            }
        };
    }

    @Bean
    public ItemWriter<Payment> paymentWriter() {
        return payments -> {
            try {
                System.out.println("Writing payments batch");
                paymentRepository.saveAll(payments);
            } catch (Exception e) {
                System.err.println("Error writing payments: " + e.getMessage());
                throw e;
            }
        };
    }

    @Bean
    public Step paymentStep(JobRepository jobRepository,
                            EntityManagerFactory entityManagerFactory,
                            PaymentReader paymentReader) {
        return new StepBuilder("paymentStep", jobRepository)
                .<Payment, Payment>chunk(10, transactionManager(entityManagerFactory))
                .reader(paymentReader)
                .processor(paymentProcessor())
                .writer(paymentWriter())
                .taskExecutor(taskExecutor())
                .faultTolerant()
                .skip(Exception.class)
                .noRetry(Exception.class)
                .listener(readListener())
                .build();
    }

    @Bean
    public Job paymentJob(JobRepository jobRepository, Step paymentStep) {
        return new JobBuilder("paymentJob", jobRepository)
                .start(paymentStep)
                .build();
    }


    @Bean
    public ItemReadListener<Payment> readListener() {
        return new ItemReadListener<Payment>() {
            @Override
            public void beforeRead() {
                System.out.println("Before reading a chunk of payments...");
            }

            @Override
            public void afterRead(Payment item) {
                System.out.println("Read payment: " + item.getTransactionId() + " with status: " + item.getPayment_status());
            }

            @Override
            public void onReadError(Exception ex) {
                System.err.println("Error while reading payment: " + ex.getMessage());
            }
        };
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.setConcurrencyLimit(10);
        return simpleAsyncTaskExecutor;
    }

}

