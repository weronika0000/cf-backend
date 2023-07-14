package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardCollectionDto;
import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardCollectionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardCollectionsService {

    final FlashcardsMapper mapper = FlashcardsMapper.INSTANCE;
    final FlashcardCollectionsRepository repository;

    FlashcardCollectionsService(FlashcardCollectionsRepository repository) {
        this.repository = repository;
    }

    FlashcardCollection findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    FlashcardCollection mapDto(CreateFlashcardCollectionDto dto) {
        return mapper.collectionToEntity(dto);
    }

    FlashcardCollectionDto toDto(FlashcardCollection collection) {
        return mapper.collectionToDto(collection);
    }

    public FlashcardCollection saveByDto(CreateFlashcardCollectionDto dto) {
        return repository.save(mapDto(dto));
    }

    public FlashcardCollectionDto addCards(Long id, List<CreateFlashcardDto> flashcards) {
        FlashcardCollection collection = repository.findById(id).orElseThrow();
        List<Flashcard> cards = flashcards.stream().map(card -> new Flashcard(card, collection)).toList();
        cards.forEach(collection::addCard);
        return toDto(repository.save(collection));
    }
}
