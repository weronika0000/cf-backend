package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardDto;
import org.springframework.stereotype.Service;

@Service
public class FlashcardsService {

    final FlashcardsMapper mapper = FlashcardsMapper.INSTANCE;
    final FlashcardCollectionsService service;
    final FlashcardsRepository repository;
    public FlashcardsService(FlashcardsRepository repository, FlashcardCollectionsService service) {
        this.repository = repository;
        this.service = service;
    }

    Flashcard findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    Flashcard mapDto(CreateFlashcardDto dto) {
        return mapper.flashcardToEntity(dto, service);
    }

    FlashcardDto mapEntity(Flashcard flashcard) {
        return mapper.toDto(flashcard);
    }
}
