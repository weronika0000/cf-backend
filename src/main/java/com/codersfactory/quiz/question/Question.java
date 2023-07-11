package com.codersfactory.quiz.question;


import com.codersfactory.article.Article;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;


@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type")
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String questionText;

    private String additionalInfo;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    private Set<QuestionOption> answers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
//
//    @ManyToOne
//    @JoinColumn(name = "quiz_id")
//    private Quiz quiz;
}