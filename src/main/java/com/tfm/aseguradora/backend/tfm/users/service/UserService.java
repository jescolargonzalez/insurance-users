package com.tfm.aseguradora.backend.tfm.users.service;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserEntity;
import com.tfm.aseguradora.backend.tfm.users.dataaccess.repository.UserJpaRepository;
import com.tfm.aseguradora.backend.tfm.users.service.domain.User;
import com.tfm.aseguradora.backend.tfm.users.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserMapper userMapper;

    public User findById(int id){
        Optional<UserEntity> entityOptional = userJpaRepository.findById(id);
        if(entityOptional.isPresent()){
           return userMapper.frontEntityToDomain(entityOptional.get());
        }
        return null;
    }
}
