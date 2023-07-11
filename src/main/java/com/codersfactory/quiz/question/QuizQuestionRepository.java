package com.codersfactory.quiz.question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionRepository extends JpaRepository<Question, Long> {

}
