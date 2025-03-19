package com.effigo.POC.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "certified_by")
    private String certifiedBy;

    @Column(name = "certification_no")
    private Long certificationNo;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "place_of_cert")
    private String placeOfCert;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name="S3_Key")
    private String s3Key;

}
