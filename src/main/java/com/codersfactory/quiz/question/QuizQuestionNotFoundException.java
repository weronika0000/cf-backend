package com.codersfactory.quiz.question;

public class QuizQuestionNotFoundException extends RuntimeException {
    public QuizQuestionNotFoundException(Long id) {
        super("Could not find quiz question with id: " + id);
    }
}
