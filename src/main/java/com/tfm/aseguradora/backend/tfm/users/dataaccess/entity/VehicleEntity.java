package com.tfm.aseguradora.backend.tfm.users.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class VehicleEntity {
    private static final long serialVersionUID = -912412431249214L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dniPropietario")
    private String dniPropietario;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "fechaMatriculacion")
    private String fechaMatriculacion;

    @Column(name = "kilometros")
    private String kilometros;

    @JoinTable(
            name = "users_vehicles",
            joinColumns = @JoinColumn(name = "vehicle_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="user_id", nullable = false)
    )
    @ManyToMany()
    private List<VehicleEntity> vehicles;

}
