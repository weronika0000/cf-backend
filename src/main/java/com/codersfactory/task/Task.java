package com.codersfactory.task;


import com.codersfactory.article.Article;
import com.codersfactory.common.entity.DifficultyLevel;
import com.codersfactory.task_assignment.TaskAssignment;
import com.codersfactory.task_solution.TaskSolution;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private Long taskId;
    private String title;
    private String content;
    private String exampleSolution;
    private String hint;
    private int numberOfPoints;
    private DifficultyLevel difficultyLevel;
    private Long creatorId;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    private Duration averageCompletionTime;
    private String technology;
    private String tests;
    private boolean ifApproved;

    @OneToMany(mappedBy = "task")
    private Set <TaskSolution> taskSolution;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article articleId;
}
