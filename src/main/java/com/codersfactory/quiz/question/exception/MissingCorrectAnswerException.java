package com.codersfactory.quiz.question.exception;

public class MissingCorrectAnswerException extends RuntimeException {
    public MissingCorrectAnswerException() {
        super("Question must have at least one correct answer");
    }

}
