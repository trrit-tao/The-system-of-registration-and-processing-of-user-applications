package com.example.pocessingrequests.repository;

import com.example.pocessingrequests.controllers.user.Role;
import com.example.pocessingrequests.repository.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRole(Role role);
}
