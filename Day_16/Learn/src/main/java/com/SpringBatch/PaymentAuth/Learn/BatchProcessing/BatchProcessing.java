//package com.SpringBatch.PaymentAuth.Learn.BatchProcessing;
//
//import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.SkipListener;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JpaPagingItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchProcessing {
//
//    @Bean
//    public Job paymentJob(JobRepository jobRepository, Step step) {
//        return new JobBuilder("paymentJob", jobRepository)
//                .start(step)
//                .build();
//    }
//
//    @Bean
//    public Step step(JobRepository jobRepository,
//                     PlatformTransactionManager transactionManager,
//                     JpaPagingItemReader<Payment> reader,
//                     ItemProcessor<Payment, Payment> processor,
//                     ItemWriter<Payment> writer) {
//        return new StepBuilder("paymentStep", jobRepository)
//                .<Payment, Payment>chunk(1, transactionManager)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .faultTolerant()
//                .skip(Exception.class)
//                .skipLimit(10)
//                .listener(new SkipListener<Payment, Payment>() {
//                    @Override
//                    public void onSkipInRead(Throwable t) {
//                        System.err.println("Skipped in Read: " + t.getMessage());
//                    }
//                    @Override
//                    public void onSkipInProcess(Payment item, Throwable t) {
//                        System.err.println("Skipped in Process: " + item + " | " + t.getMessage());
//                    }
//                    @Override
//                    public void onSkipInWrite(Payment item, Throwable t) {
//                        System.err.println("Skipped in Write: " + item + " | " + t.getMessage());
//                    }
//                })
//                .build();
//    }
//}

package com.SpringBatch.PaymentAuth.Learn.BatchProcessing;

import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.core.listener.SkipListenerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
@EnableBatchProcessing
public class BatchProcessing {
    @Bean
    public Step paymentStep(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager,
                            ItemReader<Payment> paymentItemReader,
                            ItemProcessor<Payment, Payment> paymentProcessor,
                            ItemWriter<Payment> paymentWriter) {

        return new StepBuilder("paymentStep", jobRepository)
                .<Payment, Payment>chunk(1, transactionManager)
                .reader(paymentItemReader)
                .processor(paymentProcessor)
                .writer(paymentWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10)
                .listener(new SkipListenerSupport<Payment, Payment>() {
                    @Override
                    public void onSkipInRead(Throwable t) {
                        System.err.println("Skipped in Read: " + t.getMessage());
                    }
                    @Override
                    public void onSkipInProcess(Payment item, Throwable t) {
                        System.err.println("Skipped in Process: " + item + " | " + t.getMessage());
                    }
                    @Override
                    public void onSkipInWrite(Payment item, Throwable t) {
                        System.err.println("Skipped in Write: " + item + " | " + t.getMessage());
                    }
                })
                .build();
    }

    @Bean
    public Job paymentJob(JobRepository jobRepository, Step paymentStep) {
        return new org.springframework.batch.core.job.builder.JobBuilder("paymentJob", jobRepository)
                .start(paymentStep)
                .build();
    }
}
