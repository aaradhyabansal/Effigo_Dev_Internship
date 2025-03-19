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
public class RegistrationDTO {
    private String registeredWith;
    private String classOfRegistration;
    private String placeOfRegistration;
    private String serviceRegTo;
    private LocalDate effectiveDate;
    private LocalDate expiryDate;
    private String s3Key;
}
