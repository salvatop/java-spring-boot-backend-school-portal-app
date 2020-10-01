package com.salvatop.omnivox.controller;

import com.salvatop.omnivox.model.Course;
import com.salvatop.omnivox.model.Student;
import com.salvatop.omnivox.model.Teacher;
import com.salvatop.omnivox.repository.CourseRepository;
import com.salvatop.omnivox.repository.StudentRepository;
import com.salvatop.omnivox.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelationalController {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public RelationalController(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping("/assign/student")
    public String assignCourseToStudent(@RequestParam String courseId, @RequestParam String studentId) {
        try {
            Course course = courseRepository.findCourseByCourseId(courseId).orElseThrow(Exception::new);
            Student student = studentRepository.findStudentByStudentId(studentId).orElseThrow(Exception::new);
            course.getStudents().add(student);
            student.getCourses().add(course);
            courseRepository.save(course);
            studentRepository.save(student);
            return "Course[" + courseId + "] ~ Student[" + studentId + "]";
        } catch (Exception e) {
            e.printStackTrace();
            return "Cannot find course or student!";
        }
    }

    @PostMapping("/assign/teacher")
    public String assignCourseToTeacher(@RequestParam String courseId, @RequestParam String teacherId) {
        try {
            Course course = courseRepository.findCourseByCourseId(courseId).orElseThrow(Exception::new);
            Teacher teacher = teacherRepository.findTeacherByTeacherId(teacherId).orElseThrow(Exception::new);
            course.setTeacher(teacher);
            teacher.getCourses().add(course);
            courseRepository.save(course);
            teacherRepository.save(teacher);
            return "Course[" + courseId + "] ~ Teacher[" + teacherId + "]";
        } catch (Exception e) {
            e.printStackTrace();
            return "Cannot find course or teacher!";
        }
    }
}
