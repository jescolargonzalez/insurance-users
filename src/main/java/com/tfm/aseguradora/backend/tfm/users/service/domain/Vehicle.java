package com.tfm.aseguradora.backend.tfm.users.service.domain;

import lombok.Data;

@Data
public class Vehicle {

    private Integer id;

    private String dniPropietario;

    private String matricula;

    private String marca;

    private String modelo;

    private String fechaMatriculacion;

    private Integer kilometros;


}