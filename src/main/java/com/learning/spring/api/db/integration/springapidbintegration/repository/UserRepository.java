package com.learning.spring.api.db.integration.springapidbintegration.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.spring.api.db.integration.springapidbintegration.entity.Users;

public interface UserRepository extends JpaRepository<Users, UUID> { 
    Optional<Users> findByUsername(String username);
}
