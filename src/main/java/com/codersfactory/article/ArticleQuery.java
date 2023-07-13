package com.codersfactory.article;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleQuery {

    private String title;
    private String author;
    private String technology;
    private String difficultyLevel;
    private List<String> tags;

}
