package com.codersfactory.task_assignment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;

import java.time.Instant;

public record CreateTaskAssignmentRequest(
        @Min(0) Long taskId,
        @Min(0) Long userId,
        @Future  Instant deadline,
        String additionalNotes
) {
}
