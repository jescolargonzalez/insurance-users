package com.tfm.aseguradora.backend.tfm.users.controller.mapper;

import com.tfm.aseguradora.backend.tfm.users.service.domain.*;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "surName"),
            @Mapping(source = "mail", target = "email"),
            @Mapping(source = "birthdate", target = "birthday"),
            @Mapping(source = "roles", target = "roles")
    })
    UserDto fromDomainToDTO(UserDomain userDomain);

    @Mappings({
            @Mapping(source = "name", target = "nombre"),
            @Mapping(source = "surName", target = "apellidos"),
            @Mapping(source = "email", target = "mail"),
            @Mapping(source = "birthday", target = "birthdate"),
            @Mapping(source = "password", target = "pass"),
    })
    UserDomain fromDtoToDomain(UserDto user );


}
