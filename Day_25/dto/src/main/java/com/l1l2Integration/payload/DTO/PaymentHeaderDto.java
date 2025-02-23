package com.l1l2Integration.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHeaderDto {
    private String paymentName;
    private String pay_id;
    private String pay_type;
}

