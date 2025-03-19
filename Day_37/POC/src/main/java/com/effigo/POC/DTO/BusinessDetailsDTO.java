package com.effigo.POC.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDetailsDTO {
    private String parentOrgName;
    private String parentCompanyType;
    private String orderCurrency;
    private String msmeRegistration;
    private String esiNo;
    private String pfNo;
}
