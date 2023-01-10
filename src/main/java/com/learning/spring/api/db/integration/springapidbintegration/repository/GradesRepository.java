package com.learning.spring.api.db.integration.springapidbintegration.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.spring.api.db.integration.springapidbintegration.entity.Grades;

public interface GradesRepository extends JpaRepository<Grades, UUID>{
    List<Grades> findByStudentUuid(UUID studentId);
    Grades findByStudentUuidAndCourseCode(UUID studentId, String courseCode);
}
