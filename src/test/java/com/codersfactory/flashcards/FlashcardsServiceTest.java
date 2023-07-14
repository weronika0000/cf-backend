package com.codersfactory.flashcards;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FlashcardsServiceTest {

    @Mock
    FlashcardsRepository repository;
    @InjectMocks
    FlashcardsService service;

    @Test
    public void setupTest() {
        assertNotNull(repository);
        assertNotNull(service);
    }
}
