package com.mapper.practice.Mapper;


import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.DTO.InvoiceDto;
import com.mapper.practice.Model.InvoiceEntity;
import com.mapper.practice.Model.PayloadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ConversionMapper {


    @Named("mapInvoices")
    default List<InvoiceEntity> mapInvoices(List<InvoiceDto> invoices) {
        if (invoices == null) {
            return null;
        }
        return invoices.stream()
                .map(this::invoiceDtoToEntity)
                .collect(Collectors.toList());
    }


    @Mapping(source = "invoice_type", target = "invoice_type")
    @Mapping(source = "invoice_date", target = "invoice_date", qualifiedByName = "stringToDate")
    @Mapping(source = "invoice_amount", target = "invoice_amount", qualifiedByName = "stringToDouble")
    InvoiceEntity invoiceDtoToEntity(InvoiceDto dto);


    @Named("stringToDate")
    default Date stringToDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format: " + dateStr);
        }
    }


    @Named("stringToDouble")
    default Double stringToDouble(String amtStr) {
        if (amtStr == null || amtStr.isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(amtStr);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format: " + amtStr);
        }
    }


    @Mapping(source="paymentHeaders.paymentName",target="paymentName")
    @Mapping(target="pay_id",source="paymentHeaders.pay_id")
    @Mapping(target="pay_type",source="paymentHeaders.pay_type")
    @Mapping(target="paymentReceiverName",source="paymentsReqDetails.paymentReceiverName")
    @Mapping(target="amount",source="paymentsReqDetails.amount")
    @Mapping(target="companyCode",source="paymentsReqDetails.companyCode")
    @Mapping(target="transactionCode",source="paymentsReqDetails.transactionCode")
    @Mapping(target="plant",source="paymentsReqDetails.plant")
    @Mapping(target="gst",source="paymentsReqDetails.gst",qualifiedByName = "stringToInteger")
     PayloadEntity InternalDtoToEntity(InternalDto internalDto);


    @Named("stringToInteger")
    default Integer stringToInteger(String amtStr) {
        if (amtStr == null || amtStr.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(amtStr);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format: " + amtStr);
        }
    }

}
