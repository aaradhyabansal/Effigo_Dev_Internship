package com.effigo.POC.DTO;

import lombok.*;

import java.time.LocalDate;

@Data
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
