package com.codersfactory.entity;

import com.codersfactory.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Task {
    @Id
    @GeneratedValue
    @Column(name="task_id")
    private long taskId;

    private String title;

    private String content;

    @Column(name="example_solution")
    private String exampleSolution;

    private String hint;

    @Column(name="number_of_points")
    private int numberOfPoints;

    @Column(name="difficulty_level")
    private Enum difficultyLevel;

    private User creator;

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="updated_at")
    private Instant updatedAt;

    @Column(name="average_completion_time")
    private Duration averageCompletionTime;

    private String technology;

    @OneToMany
    private List<String> tests;

    @Column(name="if_approved")
    private boolean ifApproved;

}
