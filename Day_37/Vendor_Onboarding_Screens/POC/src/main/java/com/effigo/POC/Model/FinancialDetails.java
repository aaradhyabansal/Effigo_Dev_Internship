package com.effigo.POC.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="accountNumber")
    private Long accountNo;

    @Column(name="beneficiaryName")
    private String beneficiaryName;

    @Column(name="bankName")
    private String bankName;

    @Column(name="branch")
    private String branch;

    @Column(name="ifscCode")
    private String ifscCode;

    @Column(name="state")
    private String state;

    @Column(name="currency")
    private String currency;


}
