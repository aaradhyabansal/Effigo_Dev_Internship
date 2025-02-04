package com.mapper.practice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
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

//invoice_date ka type is to be set date in db but string in external and internal
//gst ka type is to be set integer in db but string in external and internal