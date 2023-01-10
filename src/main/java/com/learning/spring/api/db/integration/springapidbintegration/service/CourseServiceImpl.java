package com.learning.spring.api.db.integration.springapidbintegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqCourseDto;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Course;
import com.learning.spring.api.db.integration.springapidbintegration.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void saveCourse(ReqCourseDto courseReq) {
        Course course = new Course();
        course.setSubject(courseReq.getSubject());
        this.courseRepository.save(course);
    }
    
}
