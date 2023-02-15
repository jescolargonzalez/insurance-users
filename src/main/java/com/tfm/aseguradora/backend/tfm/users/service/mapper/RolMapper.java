package com.tfm.aseguradora.backend.tfm.users.service.mapper;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserRolesEntity;
import com.tfm.aseguradora.backend.tfm.users.service.domain.RolDomain;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RolMapper {
    RolDomain frontEntityToDomain(UserRolesEntity UserRolesEntity);
}
