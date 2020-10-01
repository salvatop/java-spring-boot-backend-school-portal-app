package com.salvatop.omnivox.controller;

import com.salvatop.omnivox.form.StudentForm;
import com.salvatop.omnivox.model.Course;
import com.salvatop.omnivox.model.Student;
import com.salvatop.omnivox.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public Collection<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/student")
    public Student findOne(@RequestParam String studentId) throws Exception {
        return studentRepository.findStudentByStudentId(studentId).orElseThrow(Exception::new);
    }

    @PostMapping("/students")
    public Student save(@RequestBody StudentForm studentForm) {
        Student student = new Student();
        student.setStudentId(studentForm.getStudentId());
        student.setName(studentForm.getName());
        student.setCourses(new HashSet<>());
        return studentRepository.save(student);
    }

    @GetMapping("/student/{studentId}/courses")
    public Set<Course> getCoursesForStudent(@PathVariable String studentId) {
        try {
            return studentRepository.findStudentByStudentId(studentId).orElseThrow(Exception::new).getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }
}
