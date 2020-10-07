package com.salvatop.trainingmanager.repository;

import com.salvatop.trainingmanager.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Optional<Trainer> findTrainerByTrainerId(String trainerId);
}
