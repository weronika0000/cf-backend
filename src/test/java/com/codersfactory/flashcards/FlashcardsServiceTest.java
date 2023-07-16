package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlashcardsServiceTest {

    @Mock
    FlashcardsRepository repository;
    @Mock
    FlashcardCollectionsService collectionsService;
    @InjectMocks
    FlashcardsService service;

    @Test
    public void setupTest() {
        assertNotNull(repository);
        assertNotNull(service);
        assertNotNull(collectionsService);
    }

    @Test
    public void mapDtoTest() {
        FlashcardCollection collection = new FlashcardCollection();
        when(collectionsService.findById(1L)).thenReturn(collection);
        Flashcard flashcard = service.mapDto(new CreateFlashcardDto("front", "back", 1L));

        assertEquals("front", flashcard.getFront());
        assertEquals("back", flashcard.getBack());
        assertEquals(collection, flashcard.getFlashcardCollection());
    }

    @Test
    public void toDtoTest() {
        FlashcardCollection flashcardCollection = new FlashcardCollection();
        flashcardCollection.setId(1L);
        FlashcardDto dto = service.mapEntity(new Flashcard(1L, "front", "back", flashcardCollection));

        assertEquals(1L, dto.id());
        assertEquals("front", dto.front());
        assertEquals("back", dto.back());
        assertEquals(flashcardCollection.getId(), dto.flashcardCollection());
    }

    @Test
    public void findByIdTest() {
        FlashcardCollection flashcardCollection = new FlashcardCollection();
        flashcardCollection.setId(1L);
        Flashcard toReturn = new Flashcard(1L, "front", "back", flashcardCollection);
        when(repository.findById(1L))
                .thenReturn(Optional.of(toReturn));
        Flashcard optFlashcard = service.findById(1L);
        assertEquals(toReturn, optFlashcard);
        assertThrows(RuntimeException.class, () -> service.findById(2L));
    }

    @Test
    public void saveTest() {
        FlashcardCollection flashcardCollection = new FlashcardCollection();
        flashcardCollection.setId(1L);
        Flashcard flashcard = new Flashcard(1L, "front", "back", flashcardCollection);
//        service.saveEntity(flashcard);
        verify(repository).save(flashcard);
    }

    @Test
    public void deleteTest() {
//        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }
}
