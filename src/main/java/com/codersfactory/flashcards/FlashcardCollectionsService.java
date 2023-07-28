package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardCollectionDto;
import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardCollectionDto;
import com.codersfactory.flashcards.exception.EntityNotFoundException;
import com.codersfactory.flashcards.exception.UnauthorizedAction;
import com.codersfactory.flashcards.exception.UnauthorizedEditException;
import com.codersfactory.user.User;
import com.codersfactory.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardCollectionsService {

    final FlashcardsMapper mapper = FlashcardsMapper.INSTANCE;
    final FlashcardCollectionsRepository repository;
    final UserService userService;

    FlashcardCollectionsService(FlashcardCollectionsRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    FlashcardCollection findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(FlashcardCollection.class, id));
    }

    FlashcardCollection mapDTO(CreateFlashcardCollectionDto dto, User user) {
        return mapper.collectionToEntity(dto, user);
    }

    FlashcardCollectionDto toDTO(FlashcardCollection collection) {
        return mapper.collectionToDto(collection);
    }

    public FlashcardCollection saveByDTO(CreateFlashcardCollectionDto dto, String username) {
        return repository.save(mapDTO(dto, userService.findByUsername(username)));
    }

    public FlashcardCollectionDto addCards(Long id, List<CreateFlashcardDto> flashcards, String username) {
        FlashcardCollection collection = findById(id);
        if (checkIfUserIsOwner(collection, username)) {
            List<Flashcard> cards = flashcards.stream().map(card -> new Flashcard(card, collection)).toList();
            cards.forEach(collection::addCard);
            return toDTO(repository.save(collection));
        } else throw new UnauthorizedEditException(FlashcardCollection.class, UnauthorizedAction.POST);
    }

    public void deleteById(Long id, String username) {
        if (checkIfUserIsOwner(findById(id), username)) repository.deleteById(id);
        else throw new UnauthorizedEditException(FlashcardCollection.class, UnauthorizedAction.DELETE);
    }

    private boolean checkIfUserIsOwner(FlashcardCollection collection, String username) {
        return collection.getUser().getUsername().equals(username);
    }

    public FlashcardCollectionDto saveAndGetDTO(CreateFlashcardCollectionDto dto, String name) {
        return toDTO(saveByDTO(dto, name));
    }

    public FlashcardCollectionDto getDtoById(Long id) {
        return toDTO(findById(id));
    }
}
