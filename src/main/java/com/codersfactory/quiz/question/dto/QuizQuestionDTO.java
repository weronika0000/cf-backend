package com.codersfactory.quiz.question.dto;


import com.codersfactory.quiz.EnumValue;
import com.codersfactory.quiz.question.QuestionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.Set;


@Builder
public record QuizQuestionDTO(
        @Min(1)
        Long id,
        @NotBlank
        String questionText,
        @NotNull
        @Validated
        Set<QuestionOptionDTO> options,
        String additionalInfo,

        @Min(1)
        Long articleId,
        @NotNull
        @EnumValue(enumClass = QuestionType.class)
        QuestionType questionType){

}
