package com.mapper.practice.Mapper;


import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.DTO.InvoiceDto;
import com.mapper.practice.Model.InvoiceEntity;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                .map(dto -> {
                    InvoiceEntity entity = new InvoiceEntity();
                    entity.setInvoice_type(dto.getInvoice_type());
                    entity.setInvoice_amount(stringToDouble(dto.getInvoice_amount()));

                    try {
                        entity.setInvoice_date(stringToLocalDate(dto.getInvoice_date()));
                    } catch (Exception e) {
                        entity.setInvoice_date(null);
                    }

                    return entity;
                })
                .collect(Collectors.toList());
    }


    @Mapping(source = "invoice_type", target = "invoice_type")
    @Mapping(source = "invoice_date", target = "invoice_date", qualifiedByName = "stringToLocalDate")
    @Mapping(source = "invoice_amount", target = "invoice_amount", qualifiedByName = "stringToDouble")
    InvoiceEntity invoiceDtoToEntity(InvoiceDto dto);


    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {

            String[] parts = dateStr.split("-");
            if (parts.length == 3) {
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                if (day < 1 || day > 31 || month < 1 || month > 12) {
                    throw new RuntimeException("Invalid date format: " + dateStr);
                }
            }
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
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
    @Mapping(source = "invoices",target = "invoices",qualifiedByName ="mapInvoices")
    @Mapping(target="companyCode",source="paymentsReqDetails.companyCode")
    @Mapping(target="transactionCode",source="paymentsReqDetails.transactionCode")
    @Mapping(target="plant",source="paymentsReqDetails.plant")
    @Mapping(target="gst",source="paymentsReqDetails.gst",qualifiedByName = "stringToInteger")
    SuccessfulPayloadEntity InternalDtoToSuccessEntity(InternalDto internalDto);

    @Mapping(source="paymentHeaders.paymentName",target="paymentName")
    @Mapping(target="pay_id",source="paymentHeaders.pay_id")
    @Mapping(target="pay_type",source="paymentHeaders.pay_type")
    @Mapping(target="paymentReceiverName",source="paymentsReqDetails.paymentReceiverName")
    @Mapping(target="amount",source="paymentsReqDetails.amount")
    @Mapping(source = "invoices",target = "invoices",qualifiedByName ="mapInvoices")
    @Mapping(target="companyCode",source="paymentsReqDetails.companyCode")
    @Mapping(target="transactionCode",source="paymentsReqDetails.transactionCode")
    @Mapping(target="plant",source="paymentsReqDetails.plant")
    @Mapping(target="gst",source="paymentsReqDetails.gst",qualifiedByName = "stringToInteger")
    UnSuccessfulPayloadEntity InternalDtoToFailedEntity(InternalDto internalDto);


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
