package com.mapper.practice.DTO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternalDto {
    private PaymentHeaderDto paymentHeaders;
    private List<InvoiceDto> invoices;
    private PaymentsReqDetailsDto paymentsReqDetails;
}
