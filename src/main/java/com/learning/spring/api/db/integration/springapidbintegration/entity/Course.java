package com.learning.spring.api.db.integration.springapidbintegration.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.Type;



@Entity
public class Course {
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID uuid = UUID.randomUUID();

    @Column(name = "subject")
    private String subject;

    @Column(name = "code")
    private String code = RandomStringUtils.randomAlphanumeric(10);

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "course")
    private List<Grades> grades;
}
