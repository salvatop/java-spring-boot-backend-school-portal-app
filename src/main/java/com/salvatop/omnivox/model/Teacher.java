package com.salvatop.omnivox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "courses")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(unique = true, nullable = false)
    private String teacherId;

    @Column(nullable = false)
    private String name;

    @OneToMany
    @JsonIgnore
    private Set<Course> courses;
}
