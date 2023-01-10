package com.learning.spring.api.db.integration.springapidbintegration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ConverterStudetsToGradeCourseStudentsDto;
import com.learning.spring.api.db.integration.springapidbintegration.dto.GradesCourseStudentsDto;
import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqStudent;
import com.learning.spring.api.db.integration.springapidbintegration.dto.Response;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Student;
import com.learning.spring.api.db.integration.springapidbintegration.service.StudentServiceImpl;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/student")
public class StudentController {
    
    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    ConverterStudetsToGradeCourseStudentsDto converterGradesToGradesStudentCourse;

    @PostMapping("/add")
    public ResponseEntity<Response> addStudent(@RequestBody ReqStudent student) {
        this.studentService.addStudent(student);
        return new ResponseEntity<>(new Response<>(200, true, null), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Response<List<Student>>> getAll() {
        List<Student> data = this.studentService.getAll();
        return new ResponseEntity<>(new Response<>(200, true, data), HttpStatus.OK);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Response<GradesCourseStudentsDto>> details(@PathVariable UUID id) {
        return new ResponseEntity<>(new Response<>(200, true, 
            this.converterGradesToGradesStudentCourse.convert(this.studentService.details(id))), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateStudent(@PathVariable UUID id, @RequestBody ReqStudent student) {
        this.studentService.updateStudent(student, id);
        return new ResponseEntity<>(new Response<>(200, true, null), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable UUID id) {
        this.studentService.deleteById(id);
        return new ResponseEntity<>(new Response<>(200, true, null), HttpStatus.OK);
    }
}
