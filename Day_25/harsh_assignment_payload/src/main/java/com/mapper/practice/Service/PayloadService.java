package com.mapper.practice.Service;

import com.l1l2Integration.payload.DTO.ExternalDto;
import com.l1l2Integration.payload.DTO.InternalDto;
import com.l1l2Integration.payload.DTO.InvoiceDto;
import com.l1l2Integration.payload.DTO.ResultDto;
import com.mapper.practice.CustomDataType.ValidationState;

import com.mapper.practice.Enum.PaymentType;
import com.mapper.practice.Mapper.ConversionMapper;
import com.mapper.practice.Mapper.PaymentMapper;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import com.mapper.practice.Repository.InvoiceRepository;
import com.mapper.practice.Repository.SuccessfulPayloadRepository;
import com.mapper.practice.Repository.UnSuccessfulPayloadRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class PayloadService {
    @Getter
    @Setter
    private List<ExternalDto> paymentsToProcess = new ArrayList<>();
    private final InvoiceRepository invoiceRepository;
    private final SuccessfulPayloadRepository successfulPayloadRepository;
    private final UnSuccessfulPayloadRepository unSuccessfulPayloadRepository;
    private final PaymentMapper paymentMapper;
    private final ConversionMapper conversionMapper;
    private  ValidationState validationState=new ValidationState();


    public PayloadService(ValidationState validationState, ConversionMapper conversionMapper, PaymentMapper paymentMapper, UnSuccessfulPayloadRepository unSuccessfulPayloadRepository, SuccessfulPayloadRepository successfulPayloadRepository, InvoiceRepository invoiceRepository, List<ExternalDto> paymentsToProcess) {
        this.validationState = validationState;
        this.conversionMapper = conversionMapper;
        this.paymentMapper = paymentMapper;
        this.unSuccessfulPayloadRepository = unSuccessfulPayloadRepository;
        this.successfulPayloadRepository = successfulPayloadRepository;
        this.invoiceRepository = invoiceRepository;
        this.paymentsToProcess = paymentsToProcess;
    }

    public ValidationState validatePayload(ExternalDto paymentExternalDTO) {
        ValidationState validationState = new ValidationState();
        String reason="";
        boolean failed=false;
        if((paymentExternalDTO.getPaymentName()==null ||paymentExternalDTO.getPaymentName().isEmpty())) {
            reason+="Payment name is empty";
            failed = true;
        }
        if((paymentExternalDTO.getCompanyCode()==null ||paymentExternalDTO.getCompanyCode().isEmpty())) {
            reason+="Company Code is empty";
            failed = true;
        }
        if((paymentExternalDTO.getPlant()==null ||paymentExternalDTO.getPlant().isEmpty())) {
            reason+="Plant Name is empty";
            failed = true;
        }

        String status=paymentExternalDTO.getStatus();
        int n=status.length(),statusVal=7,flag=0;
        if(n==0)
            statusVal=0;
        for (int i = 0; i < n; i++) {
            char ch = status.charAt(i);
            if((int)ch<48 || (int)ch>57)
            {
                flag=1;
                break;
            }
        }
        if(flag==0)
            statusVal=9;

        if(paymentExternalDTO.getAmount()==null ||paymentExternalDTO.getAmount().isEmpty())
            statusVal=9;
        double convVal=Double.parseDouble(paymentExternalDTO.getAmount()==null?"0":paymentExternalDTO.getAmount());
        if(convVal<10000 && paymentExternalDTO.getPay_type().equals("CHEQUE")) {
            reason+="Amount is less than 10000 and Mode cannot be cheque";
            failed = true;
        }

        boolean val=Arrays.stream(PaymentType.values())
                .anyMatch(e -> e.name().equalsIgnoreCase(paymentExternalDTO.getPay_type()));

        if(!val) {
            reason+="Payment Mode Not Authorized";
            failed = true;
        }





        InternalDto internalDto = paymentMapper.payloadToDto(paymentExternalDTO);





        for (InvoiceDto invoice : internalDto.getInvoices()) {
            if (invoice.getInvoice_date() == null) {
                reason += "Invoice Date is empty";
                failed = true;
                break;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate invoiceDate;

            try {
                invoiceDate = LocalDate.parse(invoice.getInvoice_date(), formatter);
            } catch (DateTimeParseException e) {
                reason += "Date not in DD-MM-YYYY format";
                failed = true;
                break;
            }

            if (invoiceDate.isBefore(LocalDate.now())) {
                reason += "Past Invoice Date is Not Allowed";
                failed = true;
                break;
            }
        }
        validationState.setReason(reason);
        validationState.setFailed(failed);
        validationState.setStatusVal(statusVal);
        validationState.setInternalDto(internalDto);
        return validationState;
    }

    public ValidationState convertToInternal(ExternalDto paymentExternalDTO) {

      validationState = validatePayload(paymentExternalDTO);

        if(validationState.isFailed()) {
            System.out.println("Hi from failed");
            UnSuccessfulPayloadEntity payloadEntity = conversionMapper.InternalDtoToFailedEntity(validationState.getInternalDto());
            if (payloadEntity.getInvoices() != null) {
                payloadEntity.getInvoices().forEach(invoice -> invoice.setUnSuccessfulPayload(payloadEntity));
            }
            payloadEntity.setReason_failure(validationState.getReason());
            payloadEntity.setStatus(validationState.getStatusVal());
            unSuccessfulPayloadRepository.save(payloadEntity);
        }
        else {
            SuccessfulPayloadEntity payloadEntity = conversionMapper.InternalDtoToSuccessEntity(validationState.getInternalDto());
            if (payloadEntity.getInvoices() != null) {
                payloadEntity.getInvoices().forEach(invoice -> invoice.setSuccessfulPayload(payloadEntity));
            }
            payloadEntity.setStatus(validationState.getStatusVal());
            successfulPayloadRepository.save(payloadEntity);
        }
        return validationState;
    }
    @Transactional
    public  List<ResultDto> getSuccessfulPayments()
    {

        List<SuccessfulPayloadEntity> successfulPayloadEntities = successfulPayloadRepository.findAll();
        List<ResultDto> response = new ArrayList<>();

        successfulPayloadEntities.forEach(successfulPayloadEntity -> {
            response.add(paymentMapper.suPayloadToDto(successfulPayloadEntity));
        });


        return response;
    }
    @Transactional
    public  List<ResultDto> getUnSuccessfulPayments()
    {

        List<UnSuccessfulPayloadEntity> unSuccessfulPayloadEntities = unSuccessfulPayloadRepository.findAll();
        List<ResultDto> response = new ArrayList<>();

        unSuccessfulPayloadEntities.forEach(unSuccessfulPayloadEntity -> {
            response.add(paymentMapper.unPayloadToDto(unSuccessfulPayloadEntity));
        });


        return response;

    }

    public void deleteUnTransactionById(long id)
    {
        unSuccessfulPayloadRepository.deleteById(id);
    }
    public void deleteSuTransactionById(long id)
    {
        successfulPayloadRepository.deleteById(id);
    }

}
