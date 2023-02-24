package com.tfm.aseguradora.backend.tfm.users.service.mapper;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserEntity;
import com.tfm.aseguradora.backend.tfm.users.service.domain.UserDomain;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = RolMapper.class)
public interface UserMapper {

    @Mapping(source = "pass", target = "pass", ignore = true)
    UserDomain fromEntityToDomain(UserEntity userEntity);

    @Mapping(source = "roles", target = "roles", ignore = true)
    UserEntity fromDomainToEntity(UserDomain userDomain);

    UserEntity update(@MappingTarget UserEntity entity, UserDomain domain);

}