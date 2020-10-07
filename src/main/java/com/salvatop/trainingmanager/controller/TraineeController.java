package com.salvatop.trainingmanager.controller;

import com.salvatop.trainingmanager.form.TraineeForm;
import com.salvatop.trainingmanager.model.Course;
import com.salvatop.trainingmanager.model.Trainee;
import com.salvatop.trainingmanager.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
public class TraineeController {

    private final TraineeRepository traineeRepository;

    @Autowired
    public TraineeController(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @GetMapping("/trainees")
    public Collection<Trainee> findAll() {
        return traineeRepository.findAll();
    }

    @GetMapping("/trainee")
    public Trainee findOne(@RequestParam String studentId) throws Exception {
        return traineeRepository.findTraineeByTraineeId(studentId).orElseThrow(Exception::new);
    }

    @PostMapping("/trainees")
    public Trainee save(@RequestBody TraineeForm traineeForm) {
        Trainee trainee = new Trainee();
        trainee.setTraineeId(traineeForm.getTraineeId());
        trainee.setName(traineeForm.getName());
        trainee.setCourses(new HashSet<>());
        return traineeRepository.save(trainee);
    }

    @GetMapping("/trainee/{traineeId}/courses")
    public Set<Course> getCoursesForTrainee(@PathVariable String traineeId) {
        try {
            return traineeRepository.findTraineeByTraineeId(traineeId).orElseThrow(Exception::new).getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }
}
