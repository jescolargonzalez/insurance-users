package com.tfm.aseguradora.backend.tfm.users.controller;


import com.tfm.aseguradora.backend.tfm.users.service.JwtUtilService;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.*;

@Slf4j
@RestController
public class SecurityControllerImpl implements SecurityApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;


    @Override
    public ResponseEntity<TokenInfoDto>
    authenticateUser(UserAuthenticationDto userAuthentication) {
        log.info("Autenticando al usuario {}", userAuthentication.getUsuario());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthentication.getUsuario(),
                        userAuthentication.getPassword()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                userAuthentication.getUsuario());

        final String jwt = jwtUtilService.generateToken(userDetails);

        TokenInfoDto tokenInfo = new TokenInfoDto();
        tokenInfo.setJwtToken(jwt);

        return ResponseEntity.ok(tokenInfo);
    }

    @Override
    public ResponseEntity<SessionInfoDto> validateToken(String authorization) {

        authorization = authorization.replace("Bearer ", "");

        var mail = jwtUtilService.extractUsername(authorization);

        var userDetails = usuarioDetailsService.loadUserByUsername(mail);

        jwtUtilService.validateToken(authorization, userDetails);

        var sessionInfo = new SessionInfoDto();
        sessionInfo.setMail(mail);
        sessionInfo.setRoles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(sessionInfo);
    }
}