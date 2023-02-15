package com.tfm.aseguradora.backend.tfm.users.controller.mapper;


import com.tfm.aseguradora.backend.tfm.users.service.domain.*;
import com.tfm.aseguradora.generated.backend.tfm.users.controller.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VehicleDtoMapper {
    VehicleDto fromDomainToDto(VehicleDomain domain);

    VehicleDomain fromDtoToDomain(VehicleDto vehicleDto);

}