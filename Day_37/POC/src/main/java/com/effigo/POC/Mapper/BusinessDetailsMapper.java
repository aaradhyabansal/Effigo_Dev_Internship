package com.effigo.POC.Mapper;

import com.effigo.POC.DTO.BusinessDetailsDTO;
import com.effigo.POC.Model.BusinessDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessDetailsMapper {

    @Mapping(source="parentOrgName", target="parentOrgName")
    @Mapping(source="parentCompanyType", target="parentCompanyType")
    @Mapping(source="orderCurrency", target="orderCurrency")
    @Mapping(source="msmeRegistration", target="msmeRegistration")
    @Mapping(source="esiNo", target="esiNo")
    @Mapping(source="pfNo", target="pfNo")
    BusinessDetails toEntity(BusinessDetailsDTO Dto);

    @Mapping(source="parentOrgName", target="parentOrgName")
    @Mapping(source="parentCompanyType", target="parentCompanyType")
    @Mapping(source="orderCurrency", target="orderCurrency")
    @Mapping(source="msmeRegistration", target="msmeRegistration")
    @Mapping(source="esiNo", target="esiNo")
    @Mapping(source="pfNo", target="pfNo")
    BusinessDetailsDTO toDto(BusinessDetails details);
}
