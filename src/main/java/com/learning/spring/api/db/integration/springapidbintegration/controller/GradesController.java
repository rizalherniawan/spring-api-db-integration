package com.learning.spring.api.db.integration.springapidbintegration.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqGrades;
import com.learning.spring.api.db.integration.springapidbintegration.dto.Response;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Grades;
import com.learning.spring.api.db.integration.springapidbintegration.service.GradesServiceImpl;

@RestController
@RequestMapping("/v1/grades")
public class GradesController {

    @Autowired
    GradesServiceImpl gradesService;

    @PostMapping("/add")
    public ResponseEntity<Response> saveGrades(@RequestBody ReqGrades grades) {
        this.gradesService.saveGrades(grades);
        return new ResponseEntity<>(new Response<>(200, true, null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Grades>> getGradeByStudentId(@PathVariable UUID id) {
        Grades grades = this.gradesService.getGradeByStudentId(id);
        return new ResponseEntity<>(new Response<>(200, true, grades), HttpStatus.OK);
    }
}
