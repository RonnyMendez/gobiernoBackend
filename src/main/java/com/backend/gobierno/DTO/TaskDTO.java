package com.backend.gobierno.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private String summary;
    private String description;
    private int sprint;
    private int priority;
    private int type;
    private int storyPoint;
    private String risk;
}
