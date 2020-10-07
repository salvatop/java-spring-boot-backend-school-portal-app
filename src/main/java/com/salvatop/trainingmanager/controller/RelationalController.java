package com.salvatop.trainingmanager.controller;

import com.salvatop.trainingmanager.model.Course;
import com.salvatop.trainingmanager.model.Trainee;
import com.salvatop.trainingmanager.model.Trainer;
import com.salvatop.trainingmanager.repository.CourseRepository;
import com.salvatop.trainingmanager.repository.TraineeRepository;
import com.salvatop.trainingmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelationalController {

    private final CourseRepository courseRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public RelationalController(CourseRepository courseRepository, TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.courseRepository = courseRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    @PostMapping("/assign/trainee")
    public String assignCourseToTrainee(@RequestParam String courseId, @RequestParam String traineeId) {
        try {
            Course course = courseRepository.findCourseByCourseId(courseId).orElseThrow(Exception::new);
            Trainee trainee = traineeRepository.findTraineeByTraineeId(traineeId).orElseThrow(Exception::new);
            course.getTrainees().add(trainee);
            trainee.getCourses().add(course);
            courseRepository.save(course);
            traineeRepository.save(trainee);
            return "Course[" + courseId + "] ~ Trainee[" + traineeId + "]";
        } catch (Exception e) {
            e.printStackTrace();
            return "Cannot find course or trainee!";
        }
    }

    @PostMapping("/assign/trainer")
    public String assignCourseToTrainer(@RequestParam String courseId, @RequestParam String trainerId) {
        try {
            Course course = courseRepository.findCourseByCourseId(courseId).orElseThrow(Exception::new);
            Trainer trainer = trainerRepository.findTrainerByTrainerId(trainerId).orElseThrow(Exception::new);
            course.setTrainer(trainer);
            trainer.getCourses().add(course);
            courseRepository.save(course);
            trainerRepository.save(trainer);
            return "Course[" + courseId + "] ~ Trainer[" + trainerId + "]";
        } catch (Exception e) {
            e.printStackTrace();
            return "Cannot find course or trainer!";
        }
    }
}
