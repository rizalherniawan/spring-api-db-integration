package com.learning.spring.api.db.integration.springapidbintegration.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.spring.api.db.integration.springapidbintegration.entity.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

}
