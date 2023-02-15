package com.tfm.aseguradora.backend.tfm.users.controller;


import com.tfm.aseguradora.backend.tfm.users.controller.mapper.UserDtoMapper;
import com.tfm.aseguradora.backend.tfm.users.service.UserService;
import com.tfm.aseguradora.backend.tfm.users.service.domain.*;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.UsersApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

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

        var userDomain = userDtoMapper.dtoToDomain(userDto);

        userService.save(userDomain);

        // DESDE AQUI TENDRÁS QUE LLAMAR A UserService, tendrás que crear un metodo que se lllamara save y que recibira como parametro
        // un objeto ed DOMINIO User. Dado que el controlador recibe un objeto DTO, tendrás que implemetnar un metodo que convierte
        // de dto a dominio. Tendrás que llamar a este mapper antes de llamar al servicio.
        //
        // luego, haremos lo propio para convertir en el servicio de Usuario DOMINIO a Usuario ENTITY y en este punto tendrwemos el objeto listo para perisist



        return null ;
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        return UsersApi.super.deleteUser(id);
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String id) {
        UserDomain aux = userService.findById(Integer.parseInt(id));
        return ResponseEntity.ok(userDtoMapper.domainToDTO(aux));
    }

    @Override
    public ResponseEntity<Void> updateUser(String id, UserDto user) {
        return UsersApi.super.updateUser(id, user);
    }
}