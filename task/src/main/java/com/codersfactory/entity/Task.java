package com.codersfactory.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    @Column(name="task_id")
    private Long taskId;

    private String title;

    private String content;

    @Column(name="example_solution")
    private String exampleSolution;

    private String hint;

    @Column(name="number_of_points")
    private int numberOfPoints;

    @Column(name="difficulty_level")
    private DifficultyLevel difficultyLevel;

    private Long creatorId;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="updated_at")
    private Instant updatedAt;

    @Column(name="average_completion_time")
    private Duration averageCompletionTime;

    private String technology;

    private String tests;

    @Column(name="if_approved")
    private boolean ifApproved;

    @OneToMany(mappedBy = "task")
    private Set <TaskAssignment> taskAssignments;
}
