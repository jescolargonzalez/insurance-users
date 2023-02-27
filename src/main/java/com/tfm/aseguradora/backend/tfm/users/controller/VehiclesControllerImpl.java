package com.tfm.aseguradora.backend.tfm.users.controller;

import com.tfm.aseguradora.backend.tfm.users.controller.mapper.*;
import com.tfm.aseguradora.backend.tfm.users.service.*;
import com.tfm.aseguradora.backend.tfm.users.service.exception.*;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
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

    @Autowired
    private VehiclesService vehiclesService;


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return VehiclesApi.super.getRequest();
    }

    @Override
    public ResponseEntity<VehicleDto> createVehicle(VehicleDto vehicleDto) {
        try {
            var vehicleDomain = vehicleDtoMapper.fromDtoToDomain(vehicleDto);

            vehicleDomain = vehiclesService.save(vehicleDomain);

            vehicleDto = vehicleDtoMapper.fromDomainToDto(vehicleDomain);

            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDto);
        } catch (ResourceNotFoundException ex) {
            throw new BadRequestException("Resource " + ex.getResourceClass() + " with id " +
                    ex.getResourceIdentifier() + " in the request body does not exist.");
        }
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

    @Override
    public ResponseEntity<VehicleDto> getVehiclesById(Integer id) {
        var vehicleDomain = vehiclesService.findById(id);

        var vehicleDto = vehicleDtoMapper.fromDomainToDto(vehicleDomain);

        return ResponseEntity.ok(vehicleDto);
    }

}