package com.tfm.aseguradora.backend.tfm.users.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class VehicleEntity {
    private static final long serialVersionUID = -912412431249214L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "fecha_matriculacion")
    private String fechaMatriculacion;

    @Column(name = "kilometros")
    private String kilometros;

    @Column(name = "create_time")
    private LocalDate create_time;

    @Column(name = "update_time")
    private LocalDate update_time;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
