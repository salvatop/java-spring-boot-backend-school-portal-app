package com.salvatop.trainingmanager.controller;

import com.salvatop.trainingmanager.form.TeacherForm;
import com.salvatop.trainingmanager.model.Course;
import com.salvatop.trainingmanager.model.Teacher;
import com.salvatop.trainingmanager.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
public class TeacherController {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/teachers")
    public Collection<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @GetMapping("/teacher")
    public Teacher findOne(@RequestParam String teacherId) throws Exception {
        return teacherRepository.findTeacherByTeacherId(teacherId).orElseThrow(Exception::new);
    }

    @PostMapping("/teachers")
    public Teacher save(@RequestBody TeacherForm teacherForm) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherForm.getTeacherId());
        teacher.setName(teacherForm.getName());
        teacher.setCourses(new HashSet<>());
        return teacherRepository.save(teacher);
    }

    @GetMapping("/teacher/{teacherId}/courses")
    public Set<Course> getCoursesForTeacher(@PathVariable String teacherId) {
        try {
            return teacherRepository.findTeacherByTeacherId(teacherId).orElseThrow(Exception::new).getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }
}
