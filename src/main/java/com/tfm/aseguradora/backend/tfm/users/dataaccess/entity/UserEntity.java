package com.tfm.aseguradora.backend.tfm.users.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    private static final long serialVersionUID = -912412431249214L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dni")
    private String dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "mail")
    private String mail;
    @Column(name = "pass")
    private String pass;

    @Column(name = "phone")
    private String phone;

    @Column(name = "pais")
    private String pais;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "address")
    private String address;

    @Column(name = "iban")
    private String iban;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "create_time")
    private LocalDate create_time;

    @Column(name = "update_time")
    private LocalDate update_time;


    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<UserRolesEntity> roles;



}
