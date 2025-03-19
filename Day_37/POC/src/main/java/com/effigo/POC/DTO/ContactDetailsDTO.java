package com.effigo.POC.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsDTO {
    private ContactDTO level1;
    private ContactDTO level2;
    private ContactDTO level3;
}

