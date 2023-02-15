package com.tfm.aseguradora.backend.tfm.users.service.mapper;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserEntity;
import com.tfm.aseguradora.backend.tfm.users.service.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RolMapper.class)
public interface UserMapper {

    @Mapping(source = "pass", target = "pass", ignore = true)
    User frontEntityToDomain(UserEntity userEntity);
}
