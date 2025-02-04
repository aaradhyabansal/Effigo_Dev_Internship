package com.mapper.practice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDto {
    private String invoice_type;
    private String invoice_date;
    private String invoice_amount;
}
