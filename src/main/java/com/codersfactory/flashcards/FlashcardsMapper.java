package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardCollectionDto;
import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardCollectionDto;
import com.codersfactory.flashcards.dto.FlashcardDto;
import com.codersfactory.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
interface FlashcardsMapper {

    FlashcardsMapper INSTANCE = Mappers.getMapper(FlashcardsMapper.class);

    @Mapping(target = "flashcardCollection",
            expression = "java(service.findById(dto.flashcardCollection()))")
    Flashcard flashcardToEntity(CreateFlashcardDto dto, FlashcardCollectionsService service);

    default FlashcardDto toDto(Flashcard card) {
        return new FlashcardDto(card.getId(), card.getFront(), card.getBack(), card.getFlashcardCollection().getId());
    }

    default FlashcardCollection collectionToEntity(CreateFlashcardCollectionDto dto, User user) {
        FlashcardCollection flashcardCollection = new FlashcardCollection();
        flashcardCollection.setTitle(dto.title());
        flashcardCollection.setUser(user);
        return flashcardCollection;
    }

    default FlashcardCollectionDto collectionToDto(FlashcardCollection collection) {
        return new FlashcardCollectionDto(collection.getId(),
                collection.getTitle(),
                collection.getFlashcards().stream().map(this::toDto).toList(),
                collection.getUser().getId());
    }
}
