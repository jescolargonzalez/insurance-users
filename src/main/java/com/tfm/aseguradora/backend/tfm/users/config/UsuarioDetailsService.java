package com.tfm.aseguradora.backend.tfm.users.config;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.*;
import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.RolEntity;
import com.tfm.aseguradora.backend.tfm.users.dataaccess.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService  {

  @Autowired
  private UserJpaRepository userJpaRepository;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity userEntity = userJpaRepository.findByNombre(username);


    var roles = userEntity.getRoles();
    if (roles != null && !roles.isEmpty()) {
      User.UserBuilder userBuilder = User.withUsername(username);
      String encryptedPassword = userEntity.getPass();

      String [] rolesListString = roles.stream()
              .map(RolEntity::getNombre)
              .toArray(String[]::new);

      userBuilder.password(encryptedPassword).roles(rolesListString);
      return userBuilder.build();
    } else {
      throw new UsernameNotFoundException("Username [" + username + "] has not permissions");
    }
  }

}