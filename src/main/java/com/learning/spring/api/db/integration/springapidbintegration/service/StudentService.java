package com.learning.spring.api.db.integration.springapidbintegration.service;

import java.util.List;
import java.util.UUID;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqStudent;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Student;


public interface StudentService {
    public void addStudent(ReqStudent reqStudent);
    public List<Student> getAll();
    public Student getById(UUID id);
    public void deleteById(UUID id);
    public void updateStudent(ReqStudent reqStudent, UUID id);
    public Student details(UUID id);
}
