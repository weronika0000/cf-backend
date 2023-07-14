package com.codersfactory.flashcards.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FlashcardCollectionDto(
        @NotNull @Min(1) Long id,
        @NotNull @NotEmpty @NotBlank String title,
        @NotNull List<FlashcardDto> flashcards
) {
}
