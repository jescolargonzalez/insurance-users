package com.tfm.aseguradora.backend.tfm.users.service;

import com.tfm.aseguradora.backend.tfm.users.dataaccess.entity.UserEntity;
import com.tfm.aseguradora.backend.tfm.users.dataaccess.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioDetailsService implements UserDetailsService  {

  @Autowired
  private UserJpaRepository userJpaRepository;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    List<UserEntity> userEntityList = userJpaRepository.findAll();

    Map<String, String> userRoleMap = Map.of(
        "jcabelloc", "USER",
        "mlopez", "ADMIN"
    );
    var role = userRoleMap.get(username);
    if (role != null) {
      User.UserBuilder userBuilder = User.withUsername(username);
      // "secreto" => [BCrypt] => $2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq
      String encryptedPassword = "$2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq";
      userBuilder.password(encryptedPassword).roles(role);
      return userBuilder.build();
    } else {
      throw new UsernameNotFoundException(role);
    }

  }
}
