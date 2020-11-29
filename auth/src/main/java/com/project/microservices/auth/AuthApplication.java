package com.project.microservices.auth;

import java.util.Arrays;
import java.util.Optional;

import com.project.microservices.auth.model.Permission;
import com.project.microservices.auth.model.User;
import com.project.microservices.auth.repository.PermissionRepository;
import com.project.microservices.auth.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
  }

  @Bean
  CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
      BCryptPasswordEncoder passwordEncoder) {
    return args -> {
      initUsers(userRepository, permissionRepository, passwordEncoder);
    };

  }

  private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
      BCryptPasswordEncoder passwordEncoder) {

    Permission permission = null;
    Optional<Permission> permissionFound = permissionRepository.findByDescription("Admin");
    if (permissionFound.isEmpty()) {
      permission = new Permission();
      permission.setDescription("Admin");
      permission = permissionRepository.save(permission);
    } else {
      permission = permissionFound.get();
    }

    User user = new User();
    user.setUsername("admin");
    user.setIsAccountNonExpired(true);
    user.setIsAccountNonLocked(true);
    user.setIsCredentialsNonExpired(true);
    user.setIsEnabled(true);
    user.setPassword(passwordEncoder.encode("123456"));
    user.setPermissions(Arrays.asList(permission));

    Optional<User> userFound = userRepository.findByUsername("admin");
    if (userFound.isEmpty()) {
      userRepository.save(user);
    }
  }

}
