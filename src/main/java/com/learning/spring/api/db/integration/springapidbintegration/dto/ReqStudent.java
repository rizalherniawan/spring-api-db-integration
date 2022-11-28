package com.learning.spring.api.db.integration.springapidbintegration.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqStudent {
    private String name;
    private LocalDate birthDate;
}
