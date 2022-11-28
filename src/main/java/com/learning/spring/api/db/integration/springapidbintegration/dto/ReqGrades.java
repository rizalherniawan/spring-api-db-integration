package com.learning.spring.api.db.integration.springapidbintegration.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqGrades {
    private int grades;
    private UUID studentId;
}
