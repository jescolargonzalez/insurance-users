package com.tfm.aseguradora.backend.tfm.users.service;

import com.tfm.aseguradora.backend.tfm.users.service.domain.Vehicle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class VehiclesService {

    @Transactional
    void save(List<Vehicle> vehicles) {

        for (Vehicle vehicle : vehicles) {
            // SE LLAMARIA A REPOSITORIO JPA
        }

    }


}