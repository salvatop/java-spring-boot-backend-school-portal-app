package com.salvatop.trainingmanager.controller;

import com.salvatop.trainingmanager.form.CourseForm;
import com.salvatop.trainingmanager.model.Course;
import com.salvatop.trainingmanager.model.Trainee;
import com.salvatop.trainingmanager.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/courses")
    public Collection<Course> findAll() {
        return courseRepository.findAll();
    }

    @GetMapping("/course")
    public Course findOne(@RequestParam String courseId) throws Exception {
        return courseRepository.findCourseByCourseId(courseId).orElseThrow(Exception::new);
    }

    @PostMapping("/courses")
    public Course save(@RequestBody CourseForm courseForm) {
        Course course = new Course();
        course.setCourseId(courseForm.getCourseId());
        course.setTitle(courseForm.getTitle());
        course.setCredits(courseForm.getCredits());
        course.setTrainees(new HashSet<>());
        return courseRepository.save(course);
    }

    @GetMapping("/course/{courseId}/trainees")
    public Set<Trainee> getStudentsForCourse(@PathVariable String courseId) {
        try {
            return courseRepository.findCourseByCourseId(courseId).orElseThrow(Exception::new).getTrainees();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }
}
