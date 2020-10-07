package com.salvatop.trainingmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "courses")
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(unique = true, nullable = false)
    private String traineeId;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JsonIgnore
    private Set<Course> courses;
}
