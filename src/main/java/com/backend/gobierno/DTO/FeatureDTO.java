package com.backend.gobierno.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDTO {
    private String title;
    private String description;
    private List<TaskDTO> tasks;
}