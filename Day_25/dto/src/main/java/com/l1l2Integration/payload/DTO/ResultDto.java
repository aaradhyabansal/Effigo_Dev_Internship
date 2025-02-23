package com.l1l2Integration.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    private Long id;
    private String paymentName;
    private String pay_id;
    private String pay_type;
    private String paymentReceiverName;
    private String amount;
    private String companyCode;
    private String transactionCode;
    private String plant;
    private List<InvoiceDto> invoices;
    private Integer gst;
    private Integer status;
    private String reason_failure;
}
