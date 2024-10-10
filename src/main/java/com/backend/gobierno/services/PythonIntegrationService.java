package com.backend.gobierno.services;

import com.backend.gobierno.DTO.PythonPrediction;
import com.backend.gobierno.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PythonIntegrationService {

    @Autowired
    private RestTemplate restTemplate;

    public PythonPrediction getPrediction(Task task) {
        String pythonApiUrl = "http://127.0.0.1:5000/predict";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear el cuerpo de la solicitud para la API de Python
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("issueType", task.getType());
        requestBody.put("sprint", task.getSprint());
        requestBody.put("summary", task.getSummary());
        requestBody.put("status", 2); // Asumimos que el status es "To Do" inicialmente
        requestBody.put("priority", task.getPriority());
        requestBody.put("commentCount", 0);
        requestBody.put("votes", 0);
        requestBody.put("blockedBy", 0);
        requestBody.put("blocks", 0);
        requestBody.put("dependedOnBy", 0);
        requestBody.put("dependedOn", 0);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Hacer la solicitud POST a la API de Python
            ResponseEntity<PythonPrediction> response = restTemplate.postForEntity(pythonApiUrl, entity, PythonPrediction.class);
            return response.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            // Devolver un valor predeterminado en caso de fallo
            return new PythonPrediction(0, "Unknown");
        }
    }
}