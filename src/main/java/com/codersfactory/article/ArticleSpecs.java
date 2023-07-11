package com.codersfactory.article;

import lombok.Builder;

import java.util.List;

@Builder
public class ArticleSpecs {

    public static ArticleSpecs queryBuilder(String title, String author, String technology, String difficultyLevel, List<String> tags) {
        return ArticleSpecs.builder()
                .title(title)
                .author(author)
                .technology(technology)
                .difficultyLevel(difficultyLevel)
                .tags(tags)
                .build();
    }

    private String title;
    private String author;
    private String technology;
    private String difficultyLevel;
    private List<String> tags;

}
