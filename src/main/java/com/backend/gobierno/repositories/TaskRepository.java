package com.backend.gobierno.repositories;

import com.backend.gobierno.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Método para encontrar todas las tareas asociadas a una característica específica
    List<Task> findByFeatureId(Long featureId);
}