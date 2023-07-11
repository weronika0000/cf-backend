package com.codersfactory.article;

import com.codersfactory.common.entity.DifficultyLevel;
import jakarta.validation.constraints.NotNull;

public record ArticleDTO (

        @NotNull(message = "Data not be null")
        Long id,

    @NotNull(message = "Data not be null")
    String title,

    @NotNull(message = "Data not be null")
    String author,

    @NotNull(message = "Data not be null")
    String content,

    @NotNull(message = "Data not be null")
    String technology,

    @NotNull(message = "Data not be null")
    DifficultyLevel difficultyLevel

) {}
