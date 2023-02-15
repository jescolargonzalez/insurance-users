package com.tfm.aseguradora.backend.tfm.users.dataaccess.repository;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByMail(String nombre);

    Optional<UserEntity> findByDni(String dni);

}