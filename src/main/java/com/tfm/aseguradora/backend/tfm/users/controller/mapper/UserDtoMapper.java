package com.tfm.aseguradora.backend.tfm.users.controller.mapper;

import com.tfm.aseguradora.backend.tfm.users.service.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    com.tfm.aseguradora.generated.backend.tfm.users.controller.User domainToDTO( User user );
}
