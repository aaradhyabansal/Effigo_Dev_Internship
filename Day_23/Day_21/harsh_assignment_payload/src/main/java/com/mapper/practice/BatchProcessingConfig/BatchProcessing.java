package com.mapper.practice.BatchProcessingConfig;

import com.mapper.practice.CustomDataType.ValidationState;
import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.Mapper.ConversionMapper;
import com.mapper.practice.Mapper.PaymentMapper;
import com.mapper.practice.Model.InvoiceEntity;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import com.mapper.practice.Repository.InvoiceRepository;
import com.mapper.practice.Repository.SuccessfulPayloadRepository;
import com.mapper.practice.Repository.UnSuccessfulPayloadRepository;
import com.mapper.practice.Service.PayloadService;
import com.mongodb.MongoException;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.ItemWriterException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
@Slf4j
@EnableBatchProcessing
public class BatchProcessing {

    private final InvoiceRepository invoiceRepository;
    private final SuccessfulPayloadRepository successfulPayloadRepository;
    private final UnSuccessfulPayloadRepository unSuccessfulPayloadRepository;
    private final PaymentMapper paymentMapper;
    private final ConversionMapper conversionMapper;
    private final PayloadService payloadService;

    public BatchProcessing(InvoiceRepository invoiceRepository, SuccessfulPayloadRepository successfulPayloadRepository, UnSuccessfulPayloadRepository unSuccessfulPayloadRepository, PaymentMapper paymentMapper, ConversionMapper conversionMapper, PayloadService payloadService) {
        this.invoiceRepository = invoiceRepository;
        this.successfulPayloadRepository = successfulPayloadRepository;
        this.unSuccessfulPayloadRepository = unSuccessfulPayloadRepository;
        this.paymentMapper = paymentMapper;
        this.conversionMapper = conversionMapper;
        this.payloadService = payloadService;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }


    @Component
    @StepScope
    public class PaymentReader implements ItemReader<ExternalDto> {
        private Iterator<ExternalDto> paymentIterator;
        private boolean initialized = false;

        @Override
        public ExternalDto read() {
            if (!initialized) {
                List<ExternalDto> payments = payloadService.getPaymentsToProcess();
                if (payments.isEmpty()) {
                    return null;
                }
                this.paymentIterator = payments.iterator();
                initialized = true;
            }

            return paymentIterator.hasNext() ? paymentIterator.next() : null;
        }
    }




    @Bean
    public ItemProcessor<ExternalDto, ValidationState> paymentProcessor() {
        System.out.println("Inside Processor");

        return payment -> {
            try {
                System.out.println(payment);
                log.info("al payments values defined : {}",payment);
                ValidationState validationStatee = payloadService.convertToInternal(payment);

                return validationStatee;
            } catch (Exception e) {
                System.err.println("hi from here Error processing payment: " + e.getMessage());
                return null;
            }
        };
    }

    @Bean
    public ItemWriter<ValidationState> paymentWriter() {
        return payments -> {
            for (ValidationState payment : payments) {
                if (payment == null) continue;

                try {
                    if (payment.isFailed()) {
                        UnSuccessfulPayloadEntity payloadEntity = conversionMapper.InternalDtoToFailedEntity(payment.getInternalDto());
                        List<InvoiceEntity> invoices = null;
                        if (payloadEntity.getInvoices() != null) {
                            invoices = new ArrayList<>(payloadEntity.getInvoices());
                            payloadEntity.setInvoices(null);
                        }

                        UnSuccessfulPayloadEntity savedEntity = unSuccessfulPayloadRepository.save(payloadEntity);
                        if (invoices != null) {
                            invoices.forEach(invoice -> invoice.setUnSuccessfulPayload(savedEntity));
                            invoiceRepository.saveAll(invoices);
                            savedEntity.setInvoices(invoices);
                        }
                        savedEntity.setReason_failure(payment.getReason());
                        savedEntity.setStatus(payment.getStatusVal());
                        unSuccessfulPayloadRepository.save(savedEntity);
                    } else {
                        SuccessfulPayloadEntity payloadEntity = conversionMapper.InternalDtoToSuccessEntity(payment.getInternalDto());
                        List<InvoiceEntity> invoices = null;
                        if (payloadEntity.getInvoices() != null) {
                            invoices = new ArrayList<>(payloadEntity.getInvoices());
                            payloadEntity.setInvoices(null);
                        }

                        SuccessfulPayloadEntity savedEntity = successfulPayloadRepository.save(payloadEntity);

                        if (invoices != null) {
                            invoices.forEach(invoice -> invoice.setSuccessfulPayload(savedEntity));
                            invoiceRepository.saveAll(invoices);
                            savedEntity.setInvoices(invoices);
                        }
                        savedEntity.setStatus(payment.getStatusVal());
                        successfulPayloadRepository.save(savedEntity);
                    }
                } catch (Exception e) {
                    log.error("Error writing payment: {}", e.getMessage(), e);
//                    throw ("Error writing payment", e);
                }
            }
        };
    }

    @Bean
    public Step paymentStep(JobRepository jobRepository,
                            EntityManagerFactory entityManagerFactory,
                            PaymentReader paymentReader) {
        return new StepBuilder("paymentStep", jobRepository)
                .<ExternalDto, ValidationState>chunk(10, transactionManager(entityManagerFactory))
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
    public ItemReadListener<ExternalDto> readListener() {
        return new ItemReadListener<ExternalDto>() {
            @Override
            public void beforeRead() {
                System.out.println("Before reading a chunk of payments...");
            }

//            @Override
//            public void afterRead(ExternalDto item) {
//                System.out.println("Read payment: " + item.getTransactionId() + " with status: " + item.getPayment_status());
//            }

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
