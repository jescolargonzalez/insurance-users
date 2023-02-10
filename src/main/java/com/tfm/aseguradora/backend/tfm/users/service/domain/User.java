package com.tfm.aseguradora.backend.tfm.users.service.domain;


import lombok.Data;

import java.util.List;
@Data
public class User {
    private Integer id;

    private String dni;

    private String nombre;

    private String apellidos;

    private String mail;

    private String pass;

    private List<UserRol> roles;

}
