package com.tfm.aseguradora.backend.tfm.users.service;

import com.tfm.aseguradora.backend.tfm.users.service.domain.VehicleDomain;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class VehiclesService {

    @Transactional
    void save(List<VehicleDomain> vehicleDomains) {

        for (VehicleDomain vehicleDomain : vehicleDomains) {
            // SE LLAMARIA A REPOSITORIO JPA
        }

    }


}