package com.tfm.aseguradora.backend.tfm.users.service.domain;


import lombok.Data;

import java.util.List;
@Data
public class UserDomain {

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

    private String pais;

    private String ciudad;

    private byte[] picture;

    private List<RolDomain> roles;

}
