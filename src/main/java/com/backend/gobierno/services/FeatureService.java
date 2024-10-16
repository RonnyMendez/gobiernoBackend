package com.backend.gobierno.services;

import com.backend.gobierno.DTO.FeatureDTO;
import com.backend.gobierno.DTO.PythonPrediction;
import com.backend.gobierno.DTO.TaskDTO;
import com.backend.gobierno.models.Feature;
import com.backend.gobierno.models.Task;
import com.backend.gobierno.repositories.FeatureRepository;
import com.backend.gobierno.repositories.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PythonIntegrationService pythonIntegrationService;

    public Feature createFeature(FeatureDTO featureDTO) {
        Feature feature = new Feature();
        feature.setTitle(featureDTO.getTitle());
        feature.setDescription(featureDTO.getDescription());

        List<Task> tasks = new ArrayList<>();
        for (TaskDTO taskDTO : featureDTO.getTasks()) {
            Task task = new Task();
            task.setSummary(taskDTO.getSummary());
            task.setSprint(taskDTO.getSprint());
            task.setPriority(taskDTO.getPriority());
            task.setType(taskDTO.getType());  // Mapea issueType
            task.setStatus(taskDTO.getStatus());
            task.setCommentCount(taskDTO.getCommentCount());
            task.setVotes(taskDTO.getVotes());
            task.setBlockedBy(taskDTO.getBlockedBy());
            task.setBlocks(taskDTO.getBlocks());
            task.setDependedOnBy(taskDTO.getDependedOnBy());
            task.setDependedOn(taskDTO.getDependedOn());

            // Llamar a la API de Python con la tarea y obtener predicción
            PythonPrediction prediction = pythonIntegrationService.getPrediction(task);

            // Asignar la predicción a la tarea
            task.setStoryPoint(prediction.getStoryPoint());

            // Asignar la característica a la tarea
            task.setFeature(feature);
            tasks.add(task);
        }

        feature.setTasks(tasks);
        return featureRepository.save(feature); // Guardar la característica y las tareas
    }

    // Método para obtener todas las características y sus tareas asociadas
    public List<FeatureDTO> getAllFeatures() {
        List<Feature> features = featureRepository.findAll();
        return features.stream().map(feature -> {
            List<TaskDTO> taskDTOs = feature.getTasks().stream().map(task -> {
                TaskDTO taskDTO = new TaskDTO();
                taskDTO.setSummary(task.getSummary());
                taskDTO.setSprint(task.getSprint());
                taskDTO.setPriority(task.getPriority());
                taskDTO.setType(task.getType());
                taskDTO.setStoryPoint(task.getStoryPoint());
                taskDTO.setRisk(task.getRisk());
                return taskDTO;
            }).collect(Collectors.toList());

            return new FeatureDTO(feature.getTitle(), feature.getDescription(), taskDTOs);
        }).collect(Collectors.toList());
    }
}