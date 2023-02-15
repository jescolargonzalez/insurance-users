package com.tfm.aseguradora.backend.tfm.users.controller;

import com.tfm.aseguradora.generated.backend.tfm.users.controller.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.*;

import java.util.*;

@RestController
public class VehiclesControllerImpl implements VehiclesApi {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return VehiclesApi.super.getRequest();
    }

    @Override
    public ResponseEntity<VehicleDto> createVehicle(VehicleDto vehicleDto) {
        return VehiclesApi.super.createVehicle(vehicleDto);
    }

    @Override
    public ResponseEntity<VehicleDto> getVehicleByDni(String dniPropietario) {
        return VehiclesApi.super.getVehicleByDni(dniPropietario);
    }

}