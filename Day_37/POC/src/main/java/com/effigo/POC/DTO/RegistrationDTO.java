package com.effigo.POC.DTO;

import lombok.*;

import java.time.LocalDate;

@Data
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
