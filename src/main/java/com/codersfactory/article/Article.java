package com.codersfactory.article;

import com.codersfactory.common.entity.DifficultyLevel;
import com.codersfactory.quiz.Quiz;
import com.codersfactory.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name= "articles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String content;

    private String technology;

    private DifficultyLevel difficultyLevel;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "articleId")
    private Set<Quiz> quizzes = new HashSet<>();

    @OneToMany(mappedBy = "articleId")
    private Set<Task> tasks = new HashSet<>();

    @ElementCollection
    private Set<String> tags = new HashSet<>();

}
