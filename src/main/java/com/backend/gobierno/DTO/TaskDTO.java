package com.backend.gobierno.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private String summary;
    private int sprint;
    private int priority;
    private int type;  // Esto mapea el campo issueType del JSON
    private int status;
    private int commentCount;
    private int votes;
    private int blockedBy;
    private int blocks;
    private int dependedOnBy;
    private int dependedOn;
    private int storyPoint;
    private String risk;
}