package com.codersfactory.quiz.question.dto;

import com.codersfactory.quiz.question.QuestionType;

public record QuizQuestionUpdateDTO(
        Long id,
        String questionText,
        String additionalInfo,
        QuestionType questionType
) {
}
