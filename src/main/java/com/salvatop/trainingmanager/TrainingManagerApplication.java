package com.salvatop.trainingmanager;

import com.salvatop.trainingmanager.model.Course;
import com.salvatop.trainingmanager.model.Student;
import com.salvatop.trainingmanager.model.Teacher;
import com.salvatop.trainingmanager.repository.CourseRepository;
import com.salvatop.trainingmanager.repository.StudentRepository;
import com.salvatop.trainingmanager.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class TrainingManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner (StudentRepository studentRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        return args -> {
            Arrays.asList("Nir_100,Lily_200".split(","))
                    .forEach(nameId -> {
                        Student student = new Student();
                        student.setStudentId(nameId.split("_")[1]);
                        student.setName(nameId.split("_")[0]);
                        student.setCourses(new HashSet<>());
                        studentRepository.save(student);
                    });
            Arrays.asList("iOS_352,Android_420,Algorithms_242".split(","))
                    .forEach(titleId -> {
                        Course course = new Course();
                        course.setCourseId(titleId.split("_")[1]);
                        course.setTitle(titleId.split("_")[0]);
                        course.setCredits(3);
                        course.setStudents(new HashSet<>());
                        courseRepository.save(course);
                    });
            Arrays.asList("Jack_920,Alice_930".split(","))
                    .forEach(teacherId -> {
                        Teacher teacher = new Teacher();
                        teacher.setTeacherId(teacherId.split("_")[1]);
                        teacher.setName(teacherId.split("_")[0]);
                        teacher.setCourses(new HashSet<>());
                        teacherRepository.save(teacher);
                    });
        };
    }
}
