package com.mapper.practice.Service;

import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.Mapper.ConversionMapper;
import com.mapper.practice.Mapper.PaymentMapper;
import com.mapper.practice.Model.InvoiceEntity;
import com.mapper.practice.Model.PayloadEntity;
import com.mapper.practice.Repository.InvoiceRepository;
import com.mapper.practice.Repository.PayloadRepository;
import org.springframework.stereotype.Service;



@Service
public class PayloadService {
    private final InvoiceRepository invoiceRepository;
    private final PayloadRepository payloadRepository;
    private final PaymentMapper paymentMapper;
    private final ConversionMapper conversionMapper;

    public PayloadService(InvoiceRepository invoiceRepository, PayloadRepository payloadRepository, PaymentMapper paymentMapper, ConversionMapper conversionMapper) {
        this.invoiceRepository = invoiceRepository;
        this.payloadRepository = payloadRepository;
        this.paymentMapper = paymentMapper;
        this.conversionMapper = conversionMapper;
    }

    public InternalDto convertToInternal(ExternalDto paymentExternalDTO) {

        InternalDto internalDto = paymentMapper.payloadToDto(paymentExternalDTO);
        PayloadEntity payloadEntity = conversionMapper.InternalDtoToEntity(internalDto);
        if (payloadEntity.getInvoices() != null) {
            payloadEntity.getInvoices().forEach(invoice -> invoice.setPayload(payloadEntity));
        }
         payloadRepository.save(payloadEntity);
        return internalDto;
    }
}
