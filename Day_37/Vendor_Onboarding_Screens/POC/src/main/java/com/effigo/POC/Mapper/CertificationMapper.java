package com.effigo.POC.Mapper;

import com.effigo.POC.DTO.CertificationDTO;
import com.effigo.POC.Model.Certification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CertificationMapper {

    @Mapping(source="certifiedBy",target="certifiedBy")
    @Mapping(source="certificationNo",target="certificationNo")
    @Mapping(source="issuer",target="issuer")
    @Mapping(source="placeOfCert",target="placeOfCert")
    @Mapping(source="effectiveDate",target="effectiveDate")
    @Mapping(source="expiryDate",target="expiryDate")
    Certification toEntity(CertificationDTO certificationDto);

    @Mapping(source="certifiedBy",target="certifiedBy")
    @Mapping(source="certificationNo",target="certificationNo")
    @Mapping(source="issuer",target="issuer")
    @Mapping(source="placeOfCert",target="placeOfCert")
    @Mapping(source="effectiveDate",target="effectiveDate")
    @Mapping(source="expiryDate",target="expiryDate")
    CertificationDTO toDto(Certification certification);


}
