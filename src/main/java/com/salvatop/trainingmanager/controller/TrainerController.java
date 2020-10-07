package com.salvatop.trainingmanager.controller;

import com.salvatop.trainingmanager.form.TrainerForm;
import com.salvatop.trainingmanager.model.Course;
import com.salvatop.trainingmanager.model.Trainer;
import com.salvatop.trainingmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
public class TrainerController {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerController(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @GetMapping("/trainers")
    public Collection<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    @GetMapping("/trainer")
    public Trainer findOne(@RequestParam String trainerId) throws Exception {
        return trainerRepository.findTrainerByTrainerId(trainerId).orElseThrow(Exception::new);
    }

    @PostMapping("/trainers")
    public Trainer save(@RequestBody TrainerForm trainerForm) {
        Trainer trainer = new Trainer();
        trainer.setTrainerId(trainerForm.getTrainerId());
        trainer.setName(trainerForm.getName());
        trainer.setCourses(new HashSet<>());
        return trainerRepository.save(trainer);
    }

    @GetMapping("/trainer/{trainerId}/courses")
    public Set<Course> getCoursesForTrainer(@PathVariable String trainerId) {
        try {
            return trainerRepository.findTrainerByTrainerId(trainerId).orElseThrow(Exception::new).getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }
}