package com.effigo.POC.Mapper;

import com.effigo.POC.DTO.ContactDetailsDTO;
import com.effigo.POC.DTO.ContactDTO;
import com.effigo.POC.Model.ContactDetails;
import com.effigo.POC.Model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactDetailsMapper {

    ContactDetails toEntity(ContactDetailsDTO contactDetailsDTO);

    ContactDetailsDTO toDto(ContactDetails contactDetails);

    Contact toEntity(ContactDTO contactDTO);

    ContactDTO toDto(Contact contact);
}
