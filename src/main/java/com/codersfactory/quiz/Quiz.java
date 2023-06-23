package com.codersfactory.quiz;

import com.codersfactory.article.Article;
import jakarta.persistence.*;


import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Quiz {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    private Long creatorId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article articleId;

    
    @ElementCollection
    private Set<String> tags;
}
