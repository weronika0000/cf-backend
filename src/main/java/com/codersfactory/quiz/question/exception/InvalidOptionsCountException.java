package com.codersfactory.quiz.question.exception;

public class InvalidOptionsCountException extends RuntimeException {
    public InvalidOptionsCountException(String questionType, int minOptions, int maxOptions) {
        super(String.format("Question of type %s must have between %d and %d options",
                            questionType, minOptions, maxOptions));

    }
}
