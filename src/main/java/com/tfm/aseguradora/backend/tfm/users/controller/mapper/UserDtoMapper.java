package com.tfm.aseguradora.backend.tfm.users.controller.mapper;

import com.tfm.aseguradora.backend.tfm.users.service.domain.UserDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.tfm.aseguradora.generated.backend.tfm.users.controller.UserDto;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "surName"),
            @Mapping(source = "mail", target = "email"),
            @Mapping(source = "birthdate", target = "birthday"),
            @Mapping(source = "roles", target = "roles", ignore = true)
    })
    UserDto domainToDTO(UserDomain userDomain);

    @Mappings({
            @Mapping(source = "name", target = "nombre"),
            @Mapping(source = "surName", target = "apellidos"),
            @Mapping(source = "email", target = "mail"),
            @Mapping(source = "birthday", target = "birthdate"),
            @Mapping(source = "roles", target = "roles", ignore = true)
    })
    UserDomain dtoToDomain(UserDto user );


}
