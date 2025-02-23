package com.l1l2Integration.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private String invoice_type;
    private String invoice_date;
    private String invoice_amount;
}

