package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardDto;
import com.codersfactory.flashcards.exception.UnauthorizedEditException;
import com.codersfactory.user.Roles;
import com.codersfactory.user.User;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
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

    private static final long ID_1L = 1L;
    private static final String BACK = "back";
    private static final String FRONT = "front";
    private final User mockUser = new User(1L, "email@e.com", "username", "password", Roles.USER, null);
    private final FlashcardCollection collection = new FlashcardCollection(ID_1L, "title", new HashSet<>(), mockUser);
    private final Flashcard flashcard = new Flashcard(ID_1L, FRONT, BACK, collection);;


    @Before
    @DisplayName("Should create objects properly")
    public void createMockEntities() {
        collection.setUser(mockUser);
        collection.addCard(flashcard);
    }
    @Test
    @DisplayName("Should initialize mock objects properly")
    public void shouldVerifyMockObjectsNotNull() {
        assertNotNull(repository);
        assertNotNull(service);
        assertNotNull(collectionsService);
        assertNotNull(collection);
        assertNotNull(flashcard);
    }

    @Test
    public void mapDtoTest() {
        when(collectionsService.findById(1L)).thenReturn(collection);
        Flashcard mappedFlashcard = service.mapDto(new CreateFlashcardDto(FRONT, BACK, ID_1L));

        assertEquals(FRONT, mappedFlashcard.getFront());
        assertEquals(BACK, mappedFlashcard.getBack());
        assertEquals(collection, mappedFlashcard.getFlashcardCollection());
    }

    @Test
    public void toDtoTest() {
        FlashcardDto flashcardDTO = service.mapEntity(new Flashcard(ID_1L, FRONT, BACK, collection));

        assertEquals(ID_1L, flashcardDTO.id());
        assertEquals(FRONT, flashcardDTO.front());
        assertEquals(BACK, flashcardDTO.back());
        assertEquals(collection.getId(), flashcardDTO.flashcardCollection());
    }

    @Test
    public void findByIdTest() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(flashcard));
        Flashcard optFlashcard = service.findById(1L);
        assertEquals(flashcard, optFlashcard);
        assertThrows(RuntimeException.class, () -> service.findById(2L));
    }

    @Test
    public void saveTest() {
        service.saveEntity(flashcard);
        verify(repository).save(flashcard);
    }

    @Test
    public void deleteTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(flashcard));
        service.deleteById(1L, "username");
        verify(repository).deleteById(1L);
        assertThrows(UnauthorizedEditException.class, () -> service.deleteById(1L, "notCreator"));
    }
}
