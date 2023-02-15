package com.tfm.aseguradora.backend.tfm.users.controller.mapper;

import com.tfm.aseguradora.backend.tfm.users.service.domain.*;
import org.mapstruct.*;

import com.tfm.aseguradora.generated.backend.tfm.users.controller.UserDto;

import java.util.*;
import java.util.stream.*;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "surName"),
            @Mapping(source = "mail", target = "email"),
            @Mapping(source = "birthdate", target = "birthday"),
            @Mapping(source = "roles", target = "roles", ignore = true)
    })
    UserDto fromDomainToDTO(UserDomain userDomain);

    @Mappings({
            @Mapping(source = "name", target = "nombre"),
            @Mapping(source = "surName", target = "apellidos"),
            @Mapping(source = "email", target = "mail"),
            @Mapping(source = "birthday", target = "birthdate"),
            @Mapping(source = "password", target = "pass"),
            @Mapping(source = "roles", target = "roles", qualifiedByName = "fromRolesIntToDomain")
    })
    UserDomain fromDtoToDomain(UserDto user );

    @Named("fromRolesIntToDomain")
    default List<RolDomain> fromRolesIntToDomain(List<Integer> roles) {
        return roles.stream().map(id -> new RolDomain(id, null))
                .collect(Collectors.toList());
    }

}
