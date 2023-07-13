package com.codersfactory.quiz.question;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Embeddable
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class QuestionOption {

    @NotBlank
    private String answerText;

    @NotNull
    private Boolean isCorrect;


    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
