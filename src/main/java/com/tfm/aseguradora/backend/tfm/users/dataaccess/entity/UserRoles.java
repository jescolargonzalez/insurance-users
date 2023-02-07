package com.tfm.aseguradora.backend.tfm.users.dataaccess.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class UserRoles {

    private static final long serialVersionUID = -912412431249214L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "rol_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="user_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<UserRoles> roles;

}
