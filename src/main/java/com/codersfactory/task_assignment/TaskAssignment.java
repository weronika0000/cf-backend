package com.codersfactory.task_assignment;

import com.codersfactory.task.Task;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TaskAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private Long userId;

    private Instant deadline;

    private String additionalNotes;

    private Instant createdAt;

    private Instant updatedAt;

}