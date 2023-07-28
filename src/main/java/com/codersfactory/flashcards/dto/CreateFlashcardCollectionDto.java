package com.codersfactory.flashcards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateFlashcardCollectionDto(
        @NotBlank String title
) {
}
