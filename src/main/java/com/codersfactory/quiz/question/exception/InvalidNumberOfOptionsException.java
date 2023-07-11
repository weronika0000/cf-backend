package com.codersfactory.quiz.question.exception;

public class InvalidNumberOfOptionsException extends RuntimeException {
    public InvalidNumberOfOptionsException(int min, int max) {
        super(String.format("Question must have between %d and %d options", min, max));
    }
}
