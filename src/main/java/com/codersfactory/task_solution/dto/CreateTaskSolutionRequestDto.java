package com.codersfactory.task_solution.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateTaskSolutionRequestDto(
        @Min(1)
        Long taskId,

        @Min(1)
        Long userId,

        @NotBlank
        String userSolution
) {
}
