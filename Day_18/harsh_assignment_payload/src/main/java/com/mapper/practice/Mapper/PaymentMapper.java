package com.mapper.practice.Mapper;

import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

   @Mapping(source="paymentName",target="paymentHeaders.paymentName")
   @Mapping(source="pay_id",target="paymentHeaders.pay_id")
   @Mapping(source="pay_type",target="paymentHeaders.pay_type")
    @Mapping(source="invoices",target="invoices")
    @Mapping(source="paymentReceiverName",target="paymentsReqDetails.paymentReceiverName")
    @Mapping(source="amount",target="paymentsReqDetails.amount")
   @Mapping(source="companyCode",target="paymentsReqDetails.companyCode")
   @Mapping(source="transactionCode",target="paymentsReqDetails.transactionCode")
   @Mapping(source="plant",target="paymentsReqDetails.plant")
   @Mapping(source="gst",target="paymentsReqDetails.gst")
   @Mapping(source="status",target="status")
    InternalDto payloadToDto(ExternalDto externalDto);



}
