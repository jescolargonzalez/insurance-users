package com.tfm.aseguradora.backend.tfm.users.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    private static final long serialVersionUID = -912412431249214L;

    @Id
    @Column(name = "id")
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

    @Column(name = "deleted")
    private Boolean deleted;

    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="rol_id", nullable = false)
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<RolEntity> roles;


    @JoinTable(
            name = "users_vehicles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="vehicle_id", nullable = false)
    )
    @ManyToMany
    private List<VehicleEntity> vehicles;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", pais='" + pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", address='" + address + '\'' +
                ", iban='" + iban + '\'' +
                ", birthdate=" + birthdate +
                ", picture=" + Arrays.toString(picture) +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", roles=" + roles +
                '}';
    }
}
