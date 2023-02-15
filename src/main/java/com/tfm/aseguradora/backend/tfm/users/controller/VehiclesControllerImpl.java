package com.tfm.aseguradora.backend.tfm.users.controller;

import com.tfm.aseguradora.backend.tfm.users.controller.mapper.*;
import com.tfm.aseguradora.backend.tfm.users.service.*;
import com.tfm.aseguradora.backend.tfm.users.service.exception.*;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.*;

import java.util.*;
import java.util.stream.*;

@RestController
public class VehiclesControllerImpl implements VehiclesApi {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleDtoMapper vehicleDtoMapper;


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return VehiclesApi.super.getRequest();
    }

    @Override
    public ResponseEntity<VehicleDto> createVehicle(VehicleDto vehicleDto) {
        return VehiclesApi.super.createVehicle(vehicleDto);
    }

    @Override
    public ResponseEntity<VehiclesListWrapperDto> getVehiclesByDni(String dniPropietario) {

        try {
            var userDomain = userService.findByDni(dniPropietario);

            var vehiclesListDomain = userDomain.getVehicles();

            var vehiclesListDto = vehiclesListDomain.stream().map(vehicleDtoMapper::fromDomainToDto)
                    .collect(Collectors.toList());

            var response = new VehiclesListWrapperDto();
            response.setVehicles(vehiclesListDto);

            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException ex) {
            throw new BadRequestException("Resource " + ex.getResourceClass() + " with DNI " +
                    ex.getResourceIdentifier() + " does not exist.");
        }
    }

}