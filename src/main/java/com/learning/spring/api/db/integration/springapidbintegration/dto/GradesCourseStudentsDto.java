package com.learning.spring.api.db.integration.springapidbintegration.dto;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GradesCourseStudentsDto {
    UUID studentId;
    String studentName;
    List<CourseDto> course;
}
