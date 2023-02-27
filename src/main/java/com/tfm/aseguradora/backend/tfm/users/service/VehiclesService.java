package com.tfm.aseguradora.backend.tfm.users.service;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.*;
import com.tfm.aseguradora.backend.tfm.users.dataaccess.repository.*;
import com.tfm.aseguradora.backend.tfm.users.service.domain.*;
import com.tfm.aseguradora.backend.tfm.users.service.exception.*;
import com.tfm.aseguradora.backend.tfm.users.service.mapper.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class VehiclesService {

    @Autowired
    private VehicleJpaRepository vehicleJpaRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private UserJpaRepository userJpaRepository;


    public VehicleDomain save(VehicleDomain vehicle) {

        var userEntity = userJpaRepository.findById(vehicle.getUserId());

        if (userEntity.isPresent()) {
            var vehicleEntity = vehicleMapper.fromDomainToEntity(vehicle);
            vehicleEntity.setUser(userEntity.get());

            vehicleEntity = vehicleJpaRepository.save(vehicleEntity);

            return vehicleMapper.fromEntityToDomain(vehicleEntity);
        }
        else {
            throw new ResourceNotFoundException(UserDomain.class, vehicle.getUserId());
        }

    }

    public VehicleDomain findById(Integer id) {
        var vehicleOpt = vehicleJpaRepository.findById(id);

        if (vehicleOpt.isPresent()) {
            return vehicleMapper.fromEntityToDomain(vehicleOpt.get());
        }
        else {
            throw new ResourceNotFoundException(VehicleDomain.class, id);
        }
    }

}