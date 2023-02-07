package com.tfm.aseguradora.backend.tfm.users.controller;

import com.tfm.aseguradora.generated.backend.tfm.users.controller.Vehicle;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.VehiclesApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehiclesControllerImpl implements VehiclesApi {

    @Override
    public ResponseEntity<Vehicle> createVehicle(Vehicle vehicle) {
        return VehiclesApi.super.createVehicle(vehicle);
    }

    @Override
    public ResponseEntity<Vehicle> getVehicleByDni(String dniPropietario) {
        return VehiclesApi.super.getVehicleByDni(dniPropietario);
    }

}