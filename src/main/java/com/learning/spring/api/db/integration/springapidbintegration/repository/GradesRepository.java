package com.learning.spring.api.db.integration.springapidbintegration.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.spring.api.db.integration.springapidbintegration.entity.Grades;

public interface GradesRepository extends JpaRepository<Grades, String>{
    Grades findByStudentUuid(UUID studentId);
}
