package com.project.microservices.auth.repository;

import java.util.Optional;

import com.project.microservices.auth.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
