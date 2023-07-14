package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardDto;
import org.springframework.stereotype.Service;

@Service
public class FlashcardsService extends CrudService<FlashcardsRepository, Flashcard>{

    final FlashcardsMapper mapper = FlashcardsMapper.INSTANCE;
    final FlashcardCollectionsService service;
    public FlashcardsService(FlashcardsRepository repository, FlashcardCollectionsService service) {
        super(repository);
        this.service = service;
    }

    Flashcard mapDto(CreateFlashcardDto dto) {
        return mapper.flashcardToEntity(dto, service);
    }

    FlashcardDto mapEntity(Flashcard flashcard) {
        return mapper.toDto(flashcard);
    }
}
