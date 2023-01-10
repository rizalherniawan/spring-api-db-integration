package com.learning.spring.api.db.integration.springapidbintegration.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "course")
@Getter
@Setter
public class Course {
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID uuid = UUID.randomUUID();

    @Column(name = "subject")
    private String subject;

    @Column(name = "code")
    private String code = RandomStringUtils.randomAlphanumeric(10).toUpperCase();

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Grades> grades;
}
