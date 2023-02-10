package com.tfm.aseguradora.backend.tfm.users.service.mapper;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserRolesEntity;
import com.tfm.aseguradora.backend.tfm.users.service.domain.UserRol;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RolMapper {
    UserRol frontEntityToDomain(UserRolesEntity UserRolesEntity);
}
