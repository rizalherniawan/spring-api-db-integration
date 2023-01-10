package com.learning.spring.api.db.integration.springapidbintegration.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqGrades;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Course;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Grades;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Student;
import com.learning.spring.api.db.integration.springapidbintegration.exception.RequestException;
import com.learning.spring.api.db.integration.springapidbintegration.repository.CourseRepository;
import com.learning.spring.api.db.integration.springapidbintegration.repository.GradesRepository;

@Service
public class GradesServiceImpl implements GradesService {
    
    @Autowired
    GradesRepository gradeRepo;

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    StudentServiceImpl studentService;


    @Override
    public void saveGrades(ReqGrades grades) {
        int getGrades = grades.getGrades();
        if(getGrades > 100 || getGrades < 0) throw new RequestException("please input valid value");
        Student student = this.studentService.getById(grades.getStudentId());
        Grades existingStudent = this.gradeRepo.findByStudentUuidAndCourseCode(grades.getStudentId(), grades.getCourseCode());
        if(!Objects.isNull(existingStudent)) throw new RequestException("input value of student already exist");
        Course course = this.courseRepo.findByCode(grades.getCourseCode());
        if(Objects.isNull(course)) throw new RequestException("Course not found");
        Grades studGrades = new Grades();
        studGrades.setScore(getGrades);
        studGrades.setStudent(student);
        studGrades.setCourse(course);
        this.gradeRepo.save(studGrades);
    }

    @Override
    public List<Grades> getGradeByStudentId(UUID studentId) {
        List<Grades> grades = this.gradeRepo.findByStudentUuid(studentId);
        if(grades == null) throw new RequestException("Grades not found");
        return grades;
    }
}
