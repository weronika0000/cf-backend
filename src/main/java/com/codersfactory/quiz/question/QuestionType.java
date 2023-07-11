package com.codersfactory.quiz.question;


import lombok.Getter;

@Getter
public enum QuestionType {
    MULTIPLE_CHOICE (4, 8),
    SINGLE_CHOICE (4, 6),
    TYPE_IN (1, 4);

    private final int minOptions;
    private final int maxOptions;

    QuestionType(int minOptions, int maxOptions) {
        this.minOptions = minOptions;
        this.maxOptions = maxOptions;
    }

}
