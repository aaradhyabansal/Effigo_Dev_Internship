package com.mapper.practice.Mapper;

import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.Model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source="fullName",target="name")
    @Mapping(source = "emailAddress",target="email")
    InternalDto DtoToDto(ExternalDto externalDto);

    UserEntity DtoToEntity(InternalDto internalDto);

    InternalDto EntityToDto(UserEntity userEntity);

}
