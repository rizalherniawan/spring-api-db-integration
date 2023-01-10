package com.learning.spring.api.db.integration.springapidbintegration.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.spring.api.db.integration.springapidbintegration.entity.Course;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Grades;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Student;

@Service
public class ConverterStudetsToGradeCourseStudentsDto {
    public GradesCourseStudentsDto convert(Student student){
        GradesCourseStudentsDto responseDto = new GradesCourseStudentsDto();
        responseDto.setStudentId(student.getUuid());
        responseDto.setStudentName(student.getName());
        if(student.getGrades() != null){
            responseDto.setCourse(new ArrayList<>());
            for(Grades grades : student.getGrades()) {
                CourseDto course = new CourseDto();
                course.setCourseCode(grades.getCourse().getCode());
                course.setScore(grades.getScore());
                course.setSubject(grades.getCourse().getSubject());
                responseDto.getCourse().add(course);
            }
        }
        return responseDto;
    }
}
