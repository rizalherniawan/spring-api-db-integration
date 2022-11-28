package com.learning.spring.api.db.integration.springapidbintegration.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqGrades;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Grades;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Student;
import com.learning.spring.api.db.integration.springapidbintegration.exception.RequestException;
import com.learning.spring.api.db.integration.springapidbintegration.repository.GradesRepository;

@Service
public class GradesServiceImpl implements GradesService {
    
    @Autowired
    GradesRepository gradeRepo;

    @Autowired
    StudentServiceImpl studentService;


    @Override
    public void saveGrades(ReqGrades grades) {
        int getGrades = grades.getGrades();
        if(getGrades > 100 || getGrades < 0) throw new RequestException("please input valid value");
        Student student = this.studentService.getById(grades.getStudentId());
        Grades existingStudent = this.gradeRepo.findByStudentUuid(grades.getStudentId());
        if(!Objects.isNull(existingStudent)) throw new RequestException("input value of student already exist");
        Grades studGrades = new Grades();
        studGrades.setScore(getGrades);
        studGrades.setStudent(student);
        this.gradeRepo.save(studGrades);
    }

    @Override
    public Grades getGradeByStudentId(UUID studentId) {
        Grades grades = this.gradeRepo.findByStudentUuid(studentId);
        if(grades == null) throw new RequestException("Grades not found");
        return grades;
    }
}
