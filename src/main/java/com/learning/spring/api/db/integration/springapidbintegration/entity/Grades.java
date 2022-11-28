package com.learning.spring.api.db.integration.springapidbintegration.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grades")
@SQLDelete(sql = "UPDATE grades SET deleted_at = NOW() WHERE id=?")
@Getter
@Setter
public class Grades {
    
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID uuid = UUID.randomUUID();

    @Column(name = "score")
    private int score;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;


    @Override
    public String toString() {
        return "{" +
            " uuid='" + getUuid() + "'" +
            ", score='" + getScore() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", student='" + getStudent() + "'" +
            "}";
    }

}
