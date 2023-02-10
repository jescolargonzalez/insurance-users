package com.tfm.aseguradora.backend.tfm.users.service.mapper;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserEntity;
import com.tfm.aseguradora.backend.tfm.users.service.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RolMapper.class)
public interface UserMapper {

    User frontEntityToDomain(UserEntity userEntity);
}
