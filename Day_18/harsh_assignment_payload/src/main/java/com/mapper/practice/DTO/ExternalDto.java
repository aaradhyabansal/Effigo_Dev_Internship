package com.mapper.practice.DTO;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExternalDto {
   private String paymentName;
   private String pay_id;
   private String pay_type;
   private List<InvoiceDto> invoices;
   private String paymentReceiverName;
   private String amount;
   private String companyCode;
   private String transactionCode;
   private String plant;
   private String gst;
}
