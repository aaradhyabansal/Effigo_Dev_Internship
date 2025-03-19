package com.effigo.POC.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_org_name")
    private String parentOrgName;

    @Column(name = "parent_company_type")
    private String parentCompanyType;

    @Column(name = "order_currency")
    private String orderCurrency;

    @Column(name = "msme_registration")
    private String msmeRegistration;

    @Column(name = "esi_no")
    private String esiNo;

    @Column(name = "pf_no")
    private String pfNo;
}
