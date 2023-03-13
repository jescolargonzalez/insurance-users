package com.tfm.aseguradora.backend.tfm.users.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins("http://localhost:8080")
            .allowedOrigins("http://localhost:8081")
            .allowedOrigins("http://localhost:8082")
        .allowedMethods("GET", "POST", "PUT","DELETE")
        .allowCredentials(true);
  }
}


