package com.learning.spring.api.db.integration.springapidbintegration.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqStudent;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Student;
import com.learning.spring.api.db.integration.springapidbintegration.exception.NotFoundException;
import com.learning.spring.api.db.integration.springapidbintegration.exception.RequestException;
import com.learning.spring.api.db.integration.springapidbintegration.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    StudentRepository studentRepo;

    @Override
    public void addStudent(ReqStudent reqStudent) {
        Student student = new Student();
        student.setName(reqStudent.getName());
        student.setBirthDate(reqStudent.getBirthDate());
        this.studentRepo.save(student);
    }

    @Override
    public List<Student> getAll() {
        List<Student> allData = new ArrayList<>();
        this.studentRepo.findAll().forEach(student -> {
            allData.add(student);
        });
        return allData;
    }

    @Override
    public Student getById(UUID id) {
        return this.studentRepo.findById(id).orElseThrow(() -> new NotFoundException("student not found"));
    }

    @Override
    public void deleteById(UUID id) {
        this.studentRepo.deleteById(id);
    }

    @Override
    public void updateStudent(ReqStudent reqStudent, UUID id) {
        Student student = this.studentRepo.findById(id).get(); 
        if(reqStudent.getName() == null){
            student.setName(student.getName());
            student.setBirthDate(reqStudent.getBirthDate());
        } 
        else if(reqStudent.getBirthDate() == null) {
            student.setBirthDate(student.getBirthDate());
            student.setName(reqStudent.getName());
        }
        else {
            student.setBirthDate(reqStudent.getBirthDate());
            student.setName(reqStudent.getName());
        }
        this.studentRepo.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Student details(UUID id) {
        Optional<Student> student = this.studentRepo.findById(id);
        if(student.isPresent()){
            if(CollectionUtils.isNotEmpty(student.get().getGrades())) {
                Hibernate.initialize(student.get().getGrades());
            }
        return student.get();
        }
        else {
            throw new NotFoundException("student id not found");
        }
    }
}
