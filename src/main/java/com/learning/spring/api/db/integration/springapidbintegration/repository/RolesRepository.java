package com.learning.spring.api.db.integration.springapidbintegration.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.spring.api.db.integration.springapidbintegration.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, UUID>{
    
}
