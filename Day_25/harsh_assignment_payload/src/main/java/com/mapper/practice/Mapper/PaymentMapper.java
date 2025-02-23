package com.mapper.practice.Mapper;


import com.l1l2Integration.payload.DTO.ExternalDto;
import com.l1l2Integration.payload.DTO.InternalDto;
import com.l1l2Integration.payload.DTO.ResultDto;
import com.mapper.practice.Model.SuccessfulPayloadEntity;
import com.mapper.practice.Model.UnSuccessfulPayloadEntity;
import com.mapper.practice.Repository.UnSuccessfulPayloadRepository;
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


    @Mapping(source="paymentName",target="paymentName")
    @Mapping(target="pay_id",source="pay_id")
    @Mapping(target="pay_type",source="pay_type")
    @Mapping(target="paymentReceiverName",source="paymentReceiverName")
    @Mapping(target="amount",source="amount")
    @Mapping(source = "invoices",target = "invoices")
    @Mapping(target="companyCode",source="companyCode")
    @Mapping(target="transactionCode",source="transactionCode")
    @Mapping(target="plant",source="plant")
    @Mapping(target="gst",source="gst")
    @Mapping(source="reason_failure",target = "reason_failure")
   ResultDto unPayloadToDto(UnSuccessfulPayloadEntity failedPayload);

    @Mapping(source="paymentName",target="paymentName")
    @Mapping(target="pay_id",source="pay_id")
    @Mapping(target="pay_type",source="pay_type")
    @Mapping(target="paymentReceiverName",source="paymentReceiverName")
    @Mapping(target="amount",source="amount")
    @Mapping(source = "invoices",target = "invoices")
    @Mapping(target="companyCode",source="companyCode")
    @Mapping(target="transactionCode",source="transactionCode")
    @Mapping(target="plant",source="plant")
    @Mapping(target="gst",source="gst")
    ResultDto suPayloadToDto(SuccessfulPayloadEntity successfulPayload);



}
