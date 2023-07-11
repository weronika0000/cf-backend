package com.codersfactory.article;

import com.codersfactory.common.entity.DifficultyLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArticleDTO
        (@Min(1)
         Long id,

         @NotBlank
         String title,

         @NotBlank
         String author,

         @NotBlank
         String content,

         @NotBlank
         String technology,

         @NotNull
         DifficultyLevel difficultyLevel
        ) {
}
