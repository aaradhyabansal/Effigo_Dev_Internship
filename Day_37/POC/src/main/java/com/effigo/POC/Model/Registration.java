package com.effigo.POC.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registered_with")
    private String registeredWith;

    @Column(name = "class_of_registration")
    private String classOfRegistration;

    @Column(name = "place_of_registration")
    private String placeOfRegistration;

    @Column(name = "service_registered_to")
    private String serviceRegTo;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name="S3_Key")
    private String s3Key;

}
