package com.codersfactory.quiz.question;

import com.codersfactory.quiz.question.dto.QuizQuestionDTO;
import com.codersfactory.quiz.question.dto.QuizQuestionUpdateDTO;

public interface QuizQuestionService {
    QuizQuestionDTO createQuizQuestion(QuizQuestionDTO quizQuestionDTO);

    QuizQuestionDTO updateQuizQuestion(QuizQuestionUpdateDTO quizQuestionUpdateDTO);

    QuizQuestionDTO getQuizQuestionById(Long id);

    void deleteQuizQuestion(Long id);

}