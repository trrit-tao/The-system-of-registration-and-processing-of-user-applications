package com.example.pocessingrequests.repository;

import com.example.pocessingrequests.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByUserName(String userName);

}
