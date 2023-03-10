package com.tfm.aseguradora.backend.tfm.users.service;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.*;
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


    @Transactional(readOnly = true)
    public UserDomain findById(int id){
        Optional<UserEntity> entityOptional = userJpaRepository.findById(id);
        if(entityOptional.isPresent()){
           return userMapper.fromEntityToDomain(entityOptional.get());
        }
        else {
            throw new ResourceNotFoundException(UserDomain.class, id);
        }
    }
    @Transactional
    public UserDomain save(UserDomain userDomain){
        var rolesIds = userDomain.getRoles().stream().map(RolDomain::getId)
                .collect(Collectors.toList());
        var userEntity = userMapper.fromDomainToEntity(userDomain);
        var list = new ArrayList<RolEntity>();

        if (userDomain.getRoles() == null || userDomain.getRoles().isEmpty()){
            var rolClient = roleJpaRepository.findById(2);
            rolClient.ifPresent(list::add);
            userEntity.setRoles(list);
        } else{
            var roles = roleJpaRepository.findAllById(rolesIds);
            userEntity.setRoles(roles);
        }
        userEntity = userJpaRepository.save(userEntity);
        return userMapper.fromEntityToDomain(userEntity);
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
            return userMapper.fromEntityToDomain(userEntity);
        }
        else {
            throw new ResourceNotFoundException(UserDomain.class, id);
        }
    }

    @Transactional(readOnly = true)
    public UserDomain findByDni(String dniPropietario) {

        var userOpt = userJpaRepository.findByDni(dniPropietario);

        if(userOpt.isPresent()) {
            return userMapper.fromEntityToDomain(userOpt.get());
        }
        else {
            throw new ResourceNotFoundException(UserDomain.class, dniPropietario);
        }
    }

    @Transactional(readOnly = true)
    public UserDomain findByMail(String email) {
        var userOpt = userJpaRepository.findByMail(email);

        if(userOpt.isPresent()) {
            return userMapper.fromEntityToDomain(userOpt.get());
        }
        else {
            throw new ResourceNotFoundException(UserDomain.class, email);
        }
    }

    @Transactional(readOnly = true)
    public List<UserDomain> findAll() {
        var auxEnt = userJpaRepository.findAll();
        var auxDom = auxEnt.stream().map(userMapper::fromEntityToDomain).collect(Collectors.toList());

        if(!auxDom.isEmpty()){
            return auxDom;
        }else{
            throw new BadRequestException("Algo no ha ido bien");
        }

    }

}