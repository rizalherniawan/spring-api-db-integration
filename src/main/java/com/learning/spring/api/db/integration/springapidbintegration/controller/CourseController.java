package com.learning.spring.api.db.integration.springapidbintegration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqCourseDto;
import com.learning.spring.api.db.integration.springapidbintegration.dto.Response;
import com.learning.spring.api.db.integration.springapidbintegration.service.CourseServiceImpl;

@RestController
@RequestMapping("/v1/course")
public class CourseController {
    
    @Autowired
    CourseServiceImpl courseService;

    @PostMapping
    public ResponseEntity<Response> saveCourse(@RequestBody ReqCourseDto reqCourse) {
        this.courseService.saveCourse(reqCourse);
        return new ResponseEntity<>(new Response<>(200, true, null), HttpStatus.OK);
    }
}
