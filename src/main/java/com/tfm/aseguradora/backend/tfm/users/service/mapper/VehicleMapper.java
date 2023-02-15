package com.tfm.aseguradora.backend.tfm.users.service.mapper;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.*;
import com.tfm.aseguradora.backend.tfm.users.service.domain.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    VehicleEntity fromDomainToEntity(VehicleDomain vehicleDomain);

    @Mapping(source = "user.id", target = "userId")
    VehicleDomain fromEntityToDomain(VehicleEntity vehicleEntity);

}