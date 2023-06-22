package com.codersfactory.task.dto;

import com.codersfactory.common.entity.DifficultyLevel;
import jakarta.validation.constraints.*;
public record CreateTaskRequestDto(

        @NotBlank
        @Pattern(regexp = "^[A-Z].*$")
        @Size(min = 5, max = 60)
        //@Length(min = 10, max = 60)
        String title,

        @NotBlank
        @Size(min = 5)
        String content,

        @NotBlank
        @Size(min = 5)
        String exampleSolution,

        @Size(min = 5, max=500)
        String hint,

        @Min(1)
        int numberOfPoints,

        @NotNull
        DifficultyLevel difficultyLevel,

        @NotNull
        Long creatorId,

        @NotBlank
        String technology,

        @NotBlank
        String tests
){
}
