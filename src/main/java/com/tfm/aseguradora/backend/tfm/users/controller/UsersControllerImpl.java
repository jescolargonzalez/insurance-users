package com.tfm.aseguradora.backend.tfm.users.controller;


import com.tfm.aseguradora.backend.tfm.users.controller.mapper.UserDtoMapper;
import com.tfm.aseguradora.backend.tfm.users.service.UserService;
import com.tfm.aseguradora.backend.tfm.users.service.domain.*;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.UsersApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.tfm.aseguradora.generated.backend.tfm.users.controller.UserDto;

import java.util.Optional;

@Slf4j
@RestController
public class UsersControllerImpl implements UsersApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoMapper userDtoMapper;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UsersApi.super.getRequest();
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {

        var userDomain = userDtoMapper.fromDtoToDomain(userDto);

        userDomain = userService.save(userDomain);

        var userDtoResponse = userDtoMapper.fromDomainToDTO(userDomain);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDtoResponse);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        return UsersApi.super.deleteUser(id);
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String id) {
        UserDomain aux = userService.findById(Integer.parseInt(id));
        return ResponseEntity.ok(userDtoMapper.fromDomainToDTO(aux));
    }

    @Override
    public ResponseEntity<Void> updateUser(String id, UserDto user) {
        return UsersApi.super.updateUser(id, user);
    }
}