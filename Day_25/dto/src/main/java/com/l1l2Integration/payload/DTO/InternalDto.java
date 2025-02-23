package com.l1l2Integration.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternalDto {
    private PaymentHeaderDto paymentHeaders;
    private List<InvoiceDto> invoices;
    private PaymentsReqDetailsDto paymentsReqDetails;
    private String status;
}
