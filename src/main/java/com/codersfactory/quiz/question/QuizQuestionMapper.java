package com.codersfactory.quiz.question;

import com.codersfactory.quiz.question.dto.QuizQuestionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizQuestionMapper {

    QuizQuestionDTO toDto (Question question);

    Question toEntity (QuizQuestionDTO quizQuestionDTO);
}
