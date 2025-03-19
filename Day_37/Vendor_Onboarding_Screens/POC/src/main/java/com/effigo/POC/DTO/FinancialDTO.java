package com.effigo.POC.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialDTO {
    private Long accountNo;
    private String beneficiaryName;
    private String bankName;
    private String branch;
    private String ifscCode;
    private String state;
    private String currency;
}