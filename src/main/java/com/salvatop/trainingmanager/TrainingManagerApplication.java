package com.salvatop.trainingmanager;

import com.salvatop.trainingmanager.model.Course;
import com.salvatop.trainingmanager.model.Trainee;
import com.salvatop.trainingmanager.model.Trainer;
import com.salvatop.trainingmanager.repository.CourseRepository;
import com.salvatop.trainingmanager.repository.TraineeRepository;
import com.salvatop.trainingmanager.repository.TrainerRepository;
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
    CommandLineRunner runner (TraineeRepository traineeRepository, CourseRepository courseRepository, TrainerRepository trainerRepository) {
        return args -> {
            Arrays.asList("Nir_100,Lily_200".split(","))
                    .forEach(traineeId -> {
                        Trainee trainee = new Trainee();
                        trainee.setTraineeId(traineeId.split("_")[1]);
                        trainee.setName(traineeId.split("_")[0]);
                        trainee.setCourses(new HashSet<>());
                        traineeRepository.save(trainee);
                    });
            Arrays.asList("iOS_352,Android_420,Algorithms_242".split(","))
                    .forEach(titleId -> {
                        Course course = new Course();
                        course.setCourseId(titleId.split("_")[1]);
                        course.setTitle(titleId.split("_")[0]);
                        course.setCredits(3);
                        course.setTrainees(new HashSet<>());
                        courseRepository.save(course);
                    });
            Arrays.asList("Jack_920,Alice_930".split(","))
                    .forEach(trainerId -> {
                        Trainer trainer = new Trainer();
                        trainer.setTrainerId(trainerId.split("_")[1]);
                        trainer.setName(trainerId.split("_")[0]);
                        trainer.setCourses(new HashSet<>());
                        trainerRepository.save(trainer);
                    });
        };
    }
}
