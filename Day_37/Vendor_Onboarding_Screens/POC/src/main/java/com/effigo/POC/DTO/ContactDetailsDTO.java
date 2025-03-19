package com.effigo.POC.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsDTO {
    private ContactDTO level1;
    private ContactDTO level2;
    private ContactDTO level3;
}

