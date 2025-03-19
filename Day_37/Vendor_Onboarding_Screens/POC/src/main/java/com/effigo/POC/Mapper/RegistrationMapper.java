package com.effigo.POC.Mapper;

import com.effigo.POC.DTO.RegistrationDTO;
import com.effigo.POC.Model.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    @Mapping(source = "registeredWith", target = "registeredWith")
    @Mapping(source = "classOfRegistration", target = "classOfRegistration")
    @Mapping(source = "placeOfRegistration", target = "placeOfRegistration")
    @Mapping(source = "serviceRegTo", target = "serviceRegTo")
    @Mapping(source = "effectiveDate", target = "effectiveDate")
    @Mapping(source = "expiryDate", target = "expiryDate")
    @Mapping(source = "s3Key", target = "s3Key")
    Registration toEntity(RegistrationDTO dto );

    @Mapping(source = "registeredWith", target = "registeredWith")
    @Mapping(source = "classOfRegistration", target = "classOfRegistration")
    @Mapping(source = "placeOfRegistration", target = "placeOfRegistration")
    @Mapping(source = "serviceRegTo", target = "serviceRegTo")
    @Mapping(source = "effectiveDate", target = "effectiveDate")
    @Mapping(source = "expiryDate", target = "expiryDate")
    @Mapping(source = "s3Key", target = "s3Key")
    RegistrationDTO toDto(Registration registration);
}
