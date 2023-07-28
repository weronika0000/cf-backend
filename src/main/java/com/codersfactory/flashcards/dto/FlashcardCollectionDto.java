package com.codersfactory.flashcards.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FlashcardCollectionDto(
        @NotNull @Min(1) Long id,
        @NotBlank String title,
        List<FlashcardDto> flashcards,
        Long ownerId
) {
}
