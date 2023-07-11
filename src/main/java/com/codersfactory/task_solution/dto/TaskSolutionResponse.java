package com.codersfactory.task_solution.dto;

import java.time.Instant;

public record TaskSolutionResponse (
    Long id,
    Long taskId,
    Long userId,
    String userSolution,
    int pointsReceived,
    Instant createdAt,
    Instant updatedAt
) {
}
