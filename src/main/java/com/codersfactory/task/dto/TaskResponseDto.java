package com.codersfactory.task.dto;

import com.codersfactory.article.Article;
import com.codersfactory.common.entity.DifficultyLevel;
import com.codersfactory.task_solution.TaskSolution;
import lombok.Builder;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@Builder
public record TaskResponseDto(
        Long taskId,
        String title,
        String content,
        String exampleSolution,
        String hint,
        int numberOfPoints,
        DifficultyLevel difficultyLevel,
        Long creatorId,
        Instant createdAt,
        Instant updatedAt,
        Duration averageCompletionTime,
        String technology,
        String tests,
        Set<TaskSolution> taskSolution,
        Article articleId
) {
}
