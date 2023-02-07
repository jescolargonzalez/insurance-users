package com.tfm.aseguradora.backend.tfm.users.controller;


import com.tfm.aseguradora.backend.tfm.users.service.JwtUtilService;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.TokenInfo;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.User;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.UserAuthentication;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.UsersApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Slf4j
@RestController
public class UsersControllerImpl implements UsersApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UsersApi.super.getRequest();
    }

    @Override
    public ResponseEntity<TokenInfo> authenticateUser(UserAuthentication userAuthentication) {
        log.info("Autenticando al usuario {}", userAuthentication.getUsuario());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthentication.getUsuario(),
                        userAuthentication.getPassword()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                userAuthentication.getUsuario());

        final String jwt = jwtUtilService.generateToken(userDetails);

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setJwtToken(jwt);

        return ResponseEntity.ok(tokenInfo);
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        return UsersApi.super.createUser(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        return UsersApi.super.deleteUser(id);
    }

    @Override
    public ResponseEntity<User> getUserById(String id) {
        return UsersApi.super.getUserById(id);
    }

    @Override
    public ResponseEntity<Void> updateUser(String id, User user) {
        return UsersApi.super.updateUser(id, user);
    }
}