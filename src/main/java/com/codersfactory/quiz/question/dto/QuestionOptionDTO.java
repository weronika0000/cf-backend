package com.codersfactory.quiz.question.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionOptionDTO (
        @NotBlank
        String optionText,
        @NotNull
        Boolean isCorrect
) {
}
