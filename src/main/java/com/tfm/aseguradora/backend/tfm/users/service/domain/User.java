package com.tfm.aseguradora.backend.tfm.users.service.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;
@Data
public class User {
    private Integer id;

    private String dni;

    private String nombre;

    private String apellidos;

    private String mail;

    private String pass;

    private String address;

    private String iban;

    private String phone;

    private String birthdate;

    private List<UserRol> roles;

}
