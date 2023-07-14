package com.codersfactory.flashcards.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateFlashcardDto(
        @NotNull @NotEmpty @NotBlank String front,
        @NotNull @NotEmpty @NotBlank String back,
        @NotNull @Min(1) Long flashcardCollection
) {
}
