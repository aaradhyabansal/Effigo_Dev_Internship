package com.effigo.POC.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
