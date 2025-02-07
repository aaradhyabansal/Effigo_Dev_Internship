package com.mapper.practice.Service;

import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.DTO.InvoiceDto;
import com.mapper.practice.Enum.PaymentType;
import com.mapper.practice.Mapper.ConversionMapper;
import com.mapper.practice.Mapper.PaymentMapper;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import com.mapper.practice.Repository.InvoiceRepository;
import com.mapper.practice.Repository.SuccessfulPayloadRepository;
import com.mapper.practice.Repository.UnSuccessfulPayloadRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;


@Service
public class PayloadService {
    private final InvoiceRepository invoiceRepository;
    private final SuccessfulPayloadRepository successfulPayloadRepository;
    private final UnSuccessfulPayloadRepository unSuccessfulPayloadRepository;
    private final PaymentMapper paymentMapper;
    private final ConversionMapper conversionMapper;



    public PayloadService(InvoiceRepository invoiceRepository, SuccessfulPayloadRepository successfulPayloadRepository, UnSuccessfulPayloadRepository unSuccessfulPayloadRepository, PaymentMapper paymentMapper, ConversionMapper conversionMapper) {
        this.invoiceRepository = invoiceRepository;
        this.successfulPayloadRepository = successfulPayloadRepository;
        this.unSuccessfulPayloadRepository = unSuccessfulPayloadRepository;
        this.paymentMapper = paymentMapper;
        this.conversionMapper = conversionMapper;
    }

    public InternalDto convertToInternal(ExternalDto paymentExternalDTO) {

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
            if((int)ch<30 || (int)ch>39)
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

        if(failed) {
            System.out.println("Hi from failed");
            UnSuccessfulPayloadEntity payloadEntity = conversionMapper.InternalDtoToFailedEntity(internalDto);
            if (payloadEntity.getInvoices() != null) {
                payloadEntity.getInvoices().forEach(invoice -> invoice.setUnSuccessfulPayload(payloadEntity));
            }
            payloadEntity.setReason_failure(reason);
            payloadEntity.setStatus(statusVal);
            unSuccessfulPayloadRepository.save(payloadEntity);
        }
        else {
            SuccessfulPayloadEntity payloadEntity = conversionMapper.InternalDtoToSuccessEntity(internalDto);
            if (payloadEntity.getInvoices() != null) {
                payloadEntity.getInvoices().forEach(invoice -> invoice.setSuccessfulPayload(payloadEntity));
            }
            payloadEntity.setStatus(statusVal);
            successfulPayloadRepository.save(payloadEntity);
        }
        return internalDto;
    }
}
