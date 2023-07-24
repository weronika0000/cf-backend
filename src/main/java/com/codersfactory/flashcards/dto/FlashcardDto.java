package com.codersfactory.flashcards.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FlashcardDto(
        @Min(1) Long id,
        @NotBlank String front,
        @NotBlank String back,
        @Min(1) Long flashcardCollection
) {
}
