package com.learning.spring.api.db.integration.springapidbintegration.service;

import java.util.List;
import java.util.UUID;

import com.learning.spring.api.db.integration.springapidbintegration.dto.ReqGrades;
import com.learning.spring.api.db.integration.springapidbintegration.entity.Grades;

public interface GradesService {
    public void saveGrades(ReqGrades grades);
    public List<Grades> getGradeByStudentId(UUID studentId);
}