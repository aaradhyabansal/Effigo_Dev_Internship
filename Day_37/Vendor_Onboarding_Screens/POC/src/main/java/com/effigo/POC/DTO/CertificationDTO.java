package com.effigo.POC.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificationDTO {
    private String certifiedBy;
    private Long certificationNo;
    private String issuer;
    private String placeOfCert;
    private LocalDate effectiveDate;
    private LocalDate expiryDate;
    private String s3Key;
}
