package com.codersfactory.flashcards;

import com.codersfactory.flashcards.dto.CreateFlashcardCollectionDto;
import com.codersfactory.flashcards.dto.CreateFlashcardDto;
import com.codersfactory.flashcards.dto.FlashcardCollectionDto;
import com.codersfactory.flashcards.dto.FlashcardDto;
import com.codersfactory.flashcards.exception.UnauthorizedEditException;
import com.codersfactory.user.Roles;
import com.codersfactory.user.User;
import com.codersfactory.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlashcardCollectionsServiceTest {

    @Mock
    FlashcardCollectionsRepository repository;

    @Mock
    UserService userService;

    @InjectMocks
    FlashcardCollectionsService service;

    private final User mockUser = new User(1L, "email@example.com", "username",
            "password", Roles.USER, new ArrayList<>());
    private final FlashcardCollection collection = new FlashcardCollection(1L, "title",
            new HashSet<>(), mockUser);

    @Test
    @DisplayName("Should initialize mock objects properly")
    public void shouldVerifyMockObjectsNotNull() {
        assertNotNull(repository);
        assertNotNull(service);
    }

    @Test
    public void mapDtoTest() {
        CreateFlashcardCollectionDto dto = new CreateFlashcardCollectionDto("title");
        FlashcardCollection collection = service.mapDTO(dto, mockUser);
        assertEquals(dto.title(), collection.getTitle());
    }

    @Test
    public void toDtoTest() {
        FlashcardCollection collection = new FlashcardCollection();
        collection.setTitle("title");
        collection.setUser(mockUser);
        for (int i = 0; i < 10; i++) {
            collection.addCard(new Flashcard(Integer.toUnsignedLong(i), "front"+i, "back"+i, collection));
        }
        FlashcardCollectionDto check = service.toDTO(collection);

        assertNotNull(check);
        assertEquals(check.title(), collection.getTitle());
        assertEquals(10, check.flashcards().size());
    }

    @Test
    public void addCardsByDtoTest() {
        List<CreateFlashcardDto> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cards.add(new CreateFlashcardDto("front"+i, "back"+i, 1L));
        }
        when(repository.save(collection)).thenReturn(collection);
        when(repository.findById(1L)).thenReturn(Optional.of(collection));
        FlashcardCollectionDto dto = service.addCards(1L, cards, "username");

        assertEquals(dto.flashcards().size(), 10);
        verify(repository).save(any(FlashcardCollection.class));
        assertThrows(UnauthorizedEditException.class, () -> service.addCards(1L, cards, "notCreator"));
    }
}
