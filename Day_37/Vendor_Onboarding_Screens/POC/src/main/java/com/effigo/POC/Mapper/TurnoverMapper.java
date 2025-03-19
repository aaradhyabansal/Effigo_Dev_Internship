package com.effigo.POC.Mapper;

import com.effigo.POC.DTO.TurnoverDTO;
import com.effigo.POC.Model.Turnover;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TurnoverMapper {

    @Mapping(source = "turnoverYear1",target="turnoverYear1")
    @Mapping(source = "turnoverYear2",target="turnoverYear2")
    @Mapping(source = "turnoverYear3",target="turnoverYear3")
    @Mapping(source = "turnoverYear4",target="turnoverYear4")
    @Mapping(source = "turnoverYear5",target="turnoverYear5")
    Turnover toEntity(TurnoverDTO turnoverDTO);

    @Mapping(source = "turnoverYear1",target="turnoverYear1")
    @Mapping(source = "turnoverYear2",target="turnoverYear2")
    @Mapping(source = "turnoverYear3",target="turnoverYear3")
    @Mapping(source = "turnoverYear4",target="turnoverYear4")
    @Mapping(source = "turnoverYear5",target="turnoverYear5")
    TurnoverDTO toDto(Turnover turnover);
}
