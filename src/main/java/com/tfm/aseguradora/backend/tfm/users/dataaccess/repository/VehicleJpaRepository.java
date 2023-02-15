package com.tfm.aseguradora.backend.tfm.users.dataaccess.repository;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.*;
import org.springframework.data.jpa.repository.*;

public interface VehicleJpaRepository extends JpaRepository<VehicleEntity, Integer> {

}