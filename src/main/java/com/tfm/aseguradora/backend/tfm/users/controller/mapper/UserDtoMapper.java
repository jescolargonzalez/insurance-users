package com.tfm.aseguradora.backend.tfm.users.controller.mapper;

import com.tfm.aseguradora.backend.tfm.users.service.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "surName"),
            @Mapping(source = "mail", target = "email"),
            @Mapping(source = "birthdate", target = "birthday")
    })
    com.tfm.aseguradora.generated.backend.tfm.users.controller.User domainToDTO( User user );
}
