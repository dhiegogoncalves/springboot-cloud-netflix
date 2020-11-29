package com.project.microservices.auth.repository;

import java.util.Optional;

import com.project.microservices.auth.model.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

  Optional<Permission> findByDescription(String description);

}
