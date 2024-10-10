package com.backend.gobierno.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PythonPrediction {

    @JsonProperty("prediccion_storyPoint")
    private int storyPoint;

    // Constructor
    public PythonPrediction(int storyPoint, String risk) {
        this.storyPoint = storyPoint;
    }

    // Getters y Setters
    public int getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(int storyPoint) {
        this.storyPoint = storyPoint;
    }
}

