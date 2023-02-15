package com.tfm.aseguradora.backend.tfm.users.dataaccess.repository;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.*;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {


    UserEntity findByNombre(String nombre);

}