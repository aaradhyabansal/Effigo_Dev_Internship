package com.effigo.POC.Model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private String name;
    private String designation;
    private String contactNumber;
    private String emailId;
}
