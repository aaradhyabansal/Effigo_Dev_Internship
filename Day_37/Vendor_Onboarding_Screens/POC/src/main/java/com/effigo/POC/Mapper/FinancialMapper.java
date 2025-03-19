package com.effigo.POC.Mapper;

import com.effigo.POC.DTO.FinancialDTO;
import com.effigo.POC.Model.FinancialDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinancialMapper {

    FinancialDTO toDto(FinancialDetails bankDetails);

    FinancialDetails toEntity(FinancialDTO bankDetailsDTO);
}
