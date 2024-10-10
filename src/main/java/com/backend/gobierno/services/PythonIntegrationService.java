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
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PythonIntegrationService {

    @Autowired
    private RestTemplate restTemplate;

    public PythonPrediction getPrediction(Task task) {
        String pythonApiUrl = "http://127.0.0.1:5000/predict";  // URL de la API de Python
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Asegurarse de que los campos se envíen en el orden correcto
        Map<String, Object> requestBody = new LinkedHashMap<>();  // Usar LinkedHashMap para mantener el orden
        requestBody.put("issueType", task.getType());
        requestBody.put("sprint", task.getSprint());
        requestBody.put("summary", task.getSummary());
        requestBody.put("status", task.getStatus());
        requestBody.put("priority", task.getPriority());
        requestBody.put("commentCount", task.getCommentCount());
        requestBody.put("votes", task.getVotes());
        requestBody.put("blockedBy", task.getBlockedBy());
        requestBody.put("blocks", task.getBlocks());
        requestBody.put("dependedOnBy", task.getDependedOnBy());
        requestBody.put("dependedOn", task.getDependedOn());

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