package com.salvatop.trainingmanager.repository;

import com.salvatop.trainingmanager.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseByCourseId(String courseId);
}
