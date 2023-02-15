package com.tfm.aseguradora.backend.tfm.users.service;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserEntity;
import com.tfm.aseguradora.backend.tfm.users.dataaccess.repository.*;
import com.tfm.aseguradora.backend.tfm.users.service.domain.*;
import com.tfm.aseguradora.backend.tfm.users.service.mapper.UserMapper;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDomain findById(int id){
        Optional<UserEntity> entityOptional = userJpaRepository.findById(id);
        if(entityOptional.isPresent()){
           return userMapper.frontEntityToDomain(entityOptional.get());
        }
        return null;
    }
    public UserDomain save(UserDomain userDomain){
        // OBTENEMOS LOS IDS DE LOS ROLES PARA PODER OBTENERLOS DE BBDD
        var rolesIds = userDomain.getRoles().stream().map(RolDomain::getId)
                .collect(Collectors.toList());

        var roles = roleJpaRepository.findAllById(rolesIds);

        var userEntity = userMapper.fromDomainToEntity(userDomain);

        userEntity.setRoles(roles);

        userEntity = userJpaRepository.save(userEntity);

        return userMapper.frontEntityToDomain(userEntity);
    }

}