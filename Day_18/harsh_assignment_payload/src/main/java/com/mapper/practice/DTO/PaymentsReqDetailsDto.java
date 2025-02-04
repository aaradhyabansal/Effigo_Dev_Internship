package com.mapper.practice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsReqDetailsDto {
    private String paymentReceiverName;
    private String amount;
    private String companyCode;
    private String transactionCode;
    private String plant;
    private String gst;
}
