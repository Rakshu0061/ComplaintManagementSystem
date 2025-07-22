package com.example.cms.repository;

import com.example.cms.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByRoles(String roles);
}
