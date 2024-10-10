package com.backend.gobierno.controllers;

import com.backend.gobierno.DTO.FeatureDTO;
import com.backend.gobierno.models.Feature;
import com.backend.gobierno.services.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    // Método POST para crear una característica
    @PostMapping
    public ResponseEntity<Feature> createFeature(@RequestBody FeatureDTO featureDTO) {
        Feature createdFeature = featureService.createFeature(featureDTO);
        return new ResponseEntity<>(createdFeature, HttpStatus.CREATED);
    }

    // Nuevo método GET para obtener todas las características
    @GetMapping
    public ResponseEntity<List<FeatureDTO>> getAllFeatures() {
        List<FeatureDTO> features = featureService.getAllFeatures();
        return new ResponseEntity<>(features, HttpStatus.OK);
    }
}
