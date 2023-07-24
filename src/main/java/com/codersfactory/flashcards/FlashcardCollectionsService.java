package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardCollectionDto;
import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardCollectionDto;
import com.codersfactory.flashcards.exception.EntityNotFoundException;
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
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(FlashcardCollection.class, id));
    }

    FlashcardCollection mapDTO(CreateFlashcardCollectionDto dto) {
        return mapper.collectionToEntity(dto);
    }

    FlashcardCollectionDto toDTO(FlashcardCollection collection) {
        return mapper.collectionToDto(collection);
    }

    public FlashcardCollection saveByDTO(CreateFlashcardCollectionDto dto) {
        return repository.save(mapDTO(dto));
    }

    public FlashcardCollectionDto addCards(Long id, List<CreateFlashcardDto> flashcards) {
        FlashcardCollection collection = findById(id);
        List<Flashcard> cards = flashcards.stream().map(card -> new Flashcard(card, collection)).toList();
        cards.forEach(collection::addCard);
        return toDTO(repository.save(collection));
    }
}
