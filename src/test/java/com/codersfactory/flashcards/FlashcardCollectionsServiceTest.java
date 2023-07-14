package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardCollectionDto;
import com.codersfactory.flashcards.dto.FlashcardCollectionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FlashcardCollectionsServiceTest {

    @Mock
    FlashcardCollectionsRepository repository;

    @InjectMocks
    FlashcardCollectionsService service;

    @Test
    public void testNotNull() {
        assertNotNull(repository);
        assertNotNull(service);
    }

    @Test
    public void mapDtoTest() {
        CreateFlashcardCollectionDto dto = new CreateFlashcardCollectionDto("title");
        FlashcardCollection collection = service.mapDto(dto);
        assertEquals(dto.title(), collection.getTitle());
    }

    @Test
    public void toDtoTest() {
        FlashcardCollection collection = new FlashcardCollection();
        collection.setTitle("title");
        for (int i = 0; i < 10; i++) {
            collection.addCard(new Flashcard(Integer.toUnsignedLong(i), "front"+i, "back"+i, collection));
        }
        System.out.println("essa");
        FlashcardCollectionDto check = service.toDto(collection);

        assertNotNull(check);
        assertEquals(check.title(), collection.getTitle());
        assertEquals(10, check.flashcards().size());
    }
}
