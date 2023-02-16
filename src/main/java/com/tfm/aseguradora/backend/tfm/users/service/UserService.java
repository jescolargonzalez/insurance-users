package com.tfm.aseguradora.backend.tfm.users.service;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserEntity;
import com.tfm.aseguradora.backend.tfm.users.dataaccess.repository.*;
import com.tfm.aseguradora.backend.tfm.users.service.domain.*;
import com.tfm.aseguradora.backend.tfm.users.service.exception.*;
import com.tfm.aseguradora.backend.tfm.users.service.mapper.UserMapper;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

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
        else {
            throw new ResourceNotFoundException(UserDomain.class, id);
        }
    }
    public UserDomain save(UserDomain userDomain){
        var rolesIds = userDomain.getRoles().stream().map(RolDomain::getId)
                .collect(Collectors.toList());

        var roles = roleJpaRepository.findAllById(rolesIds);

        var userEntity = userMapper.fromDomainToEntity(userDomain);

        userEntity.setRoles(roles);

        userEntity = userJpaRepository.save(userEntity);

        return userMapper.frontEntityToDomain(userEntity);
    }

    @Transactional
    public void deleteById(Integer id) {
        var userOpt = userJpaRepository.findById(id);

        if (userOpt.isPresent()) {
            var user = userOpt.get();
            user.setDeleted(Boolean.TRUE);
        }
        else {
            throw new ResourceNotFoundException(UserDomain.class, id);
        }
    }

    @Transactional
    public UserDomain updateUserById(Integer id, UserDomain userDomain) {
        var userOpt = userJpaRepository.findById(id);

        if (userOpt.isPresent()) {
            var userEntity = userOpt.get();
            userEntity = userMapper.update(userEntity, userDomain);
            return userMapper.frontEntityToDomain(userEntity);
        }
        else {
            throw new ResourceNotFoundException(UserDomain.class, id);
        }
    }

    public UserDomain findByDni(String dniPropietario) {

        var userOpt = userJpaRepository.findByDni(dniPropietario);

        if(userOpt.isPresent()) {
            return userMapper.frontEntityToDomain(userOpt.get());
        }
        else {
            throw new ResourceNotFoundException(UserDomain.class, dniPropietario);
        }
    }

}