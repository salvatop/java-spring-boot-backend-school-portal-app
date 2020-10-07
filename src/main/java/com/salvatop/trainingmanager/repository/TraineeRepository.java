package com.salvatop.trainingmanager.repository;

import com.salvatop.trainingmanager.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    Optional<Trainee> findTraineeByTraineeId(String traineeId);
}
