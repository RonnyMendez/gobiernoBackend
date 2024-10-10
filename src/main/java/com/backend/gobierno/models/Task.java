package com.backend.gobierno.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int sprint;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    private int type;

    private int storyPoint;

    private String risk;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;
}
