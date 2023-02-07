package com.tfm.aseguradora.backend.tfm.users.dataaccess.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    private static final long serialVersionUID = -912412431249214L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dni;

    private String nombre;

    private String apellidos;

    private String mail;

    private String pass;

    @ManyToMany(mappedBy = "roles" , fetch = FetchType.EAGER)
    private List<UserRoles> roles;



}
