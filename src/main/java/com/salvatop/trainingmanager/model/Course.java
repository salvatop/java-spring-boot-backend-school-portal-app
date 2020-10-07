package com.salvatop.trainingmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"students", "teacher"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(unique = true, nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int credits;

    @ManyToMany
    @JsonIgnore
    private Set<Student> students;

    @ManyToOne
    @JsonIgnore
    private Teacher teacher;
}
