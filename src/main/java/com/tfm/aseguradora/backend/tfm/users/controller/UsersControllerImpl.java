package com.tfm.aseguradora.backend.tfm.users.controller;

import com.tfm.aseguradora.backend.tfm.users.controller.mapper.UserDtoMapper;
import com.tfm.aseguradora.backend.tfm.users.service.UserService;
import com.tfm.aseguradora.backend.tfm.users.service.domain.*;

import com.tfm.aseguradora.generated.backend.tfm.users.controller.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.*;
import java.util.stream.*;

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
        userService.deleteById(Integer.parseInt(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String id) {
        UserDomain aux = userService.findById(Integer.parseInt(id));
        return ResponseEntity.ok(userDtoMapper.fromDomainToDTO(aux));
    }

    @Override
    public ResponseEntity<UsersListWrapperDto> getUsers(String authorization, String dni, String mail) {
        if (dni != null) {
            UserDomain aux = userService.findByDni(dni);
            var userDto = userDtoMapper.fromDomainToDTO(aux);
            var userDtoList = new ArrayList<UserDto>();
            userDtoList.add(userDto);
            var response = new UsersListWrapperDto();
            response.setUsers(userDtoList);
            return ResponseEntity.ok(response);
        }
        else if (mail != null) {
            UserDomain aux = userService.findByMail(mail);
            var userDto = userDtoMapper.fromDomainToDTO(aux);
            var userDtoList = new ArrayList<UserDto>();
            userDtoList.add(userDto);
            var response = new UsersListWrapperDto();
            response.setUsers(userDtoList);
            return ResponseEntity.ok(response);
        }
        else {
            var aux = userService.findAll();
            var aux1 = aux.stream().map(userDtoMapper::fromDomainToDTO).collect(Collectors.toList());
            var response = new UsersListWrapperDto();
            response.setUsers(aux1);
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<UserDto> updateUser(String id, UserDto userDto) {
        userDto.setId(Long.valueOf(id));

        var userDomain = userDtoMapper.fromDtoToDomain(userDto);

        userDomain = userService.updateUserById(Integer.parseInt(id), userDomain);

        return ResponseEntity.ok(userDtoMapper.fromDomainToDTO(userDomain));
    }

}