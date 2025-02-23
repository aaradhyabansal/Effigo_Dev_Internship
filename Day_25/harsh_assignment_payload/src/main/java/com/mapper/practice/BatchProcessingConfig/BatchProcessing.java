package com.mapper.practice.BatchProcessingConfig;

import com.l1l2Integration.payload.DTO.ExternalDto;
import com.mapper.practice.CustomDataType.ValidationState;

import com.mapper.practice.Mapper.ConversionMapper;
import com.mapper.practice.Mapper.PaymentMapper;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import com.mapper.practice.Repository.InvoiceRepository;
import com.mapper.practice.Repository.SuccessfulPayloadRepository;
import com.mapper.practice.Repository.UnSuccessfulPayloadRepository;
import com.mapper.practice.Service.PayloadService;
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
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
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
    public class PaymentReader implements ItemStreamReader<ExternalDto> {
        private JsonItemReader<ExternalDto> delegate;

        public PaymentReader() {
            JacksonJsonObjectReader<ExternalDto> jsonObjectReader = new JacksonJsonObjectReader<>(ExternalDto.class);

            delegate = new JsonItemReaderBuilder<ExternalDto>()
                    .jsonObjectReader(jsonObjectReader)
                    .resource(new ClassPathResource("payload.json"))
                    .name("payloadJsonItemReader")
                    .build();
        }

        @Override
        public ExternalDto read() throws Exception {
            return delegate.read();
        }

        @Override
        public void open(ExecutionContext executionContext) throws ItemStreamException {
            delegate.open(executionContext);
        }

        @Override
        public void update(ExecutionContext executionContext) throws ItemStreamException {
            delegate.update(executionContext);
        }

        @Override
        public void close() throws ItemStreamException {
            delegate.close();
        }
    }




    @Bean
    public ItemProcessor<ExternalDto, ValidationState> paymentProcessor() {
        System.out.println("Inside Processor");

        return payment -> {
            try {
                System.out.println(payment);
                log.info("al payments values defined : {}",payment);
                return payloadService.convertToInternal(payment);
            } catch (Exception e) {
                System.err.println("Error processing payment: " + e.getMessage());
                return null;
            }
        };
    }

    @Bean
    public ItemWriter<ValidationState> paymentWriter() {
        System.out.println("Inside writer");

        return payments -> {
            for(ValidationState payment: payments) {
                if(payment.isFailed())
                {
                    UnSuccessfulPayloadEntity payloadEntity = conversionMapper.InternalDtoToFailedEntity(payment.getInternalDto());
                    if (payloadEntity.getInvoices() != null) {
                        payloadEntity.getInvoices().forEach(invoice -> invoice.setUnSuccessfulPayload(payloadEntity));
                    }
                    payloadEntity.setReason_failure(payment.getReason());
                    payloadEntity.setStatus(payment.getStatusVal());
                    unSuccessfulPayloadRepository.save(payloadEntity);
                }
                else {
                    SuccessfulPayloadEntity payloadEntity = conversionMapper.InternalDtoToSuccessEntity(payment.getInternalDto());
                    if (payloadEntity.getInvoices() != null) {
                        payloadEntity.getInvoices().forEach(invoice -> invoice.setSuccessfulPayload(payloadEntity));
                    }
                    payloadEntity.setStatus(payment.getStatusVal());
                    successfulPayloadRepository.save(payloadEntity);
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
