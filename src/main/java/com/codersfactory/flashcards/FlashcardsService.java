package com.codersfactory.flashcards;

import org.springframework.stereotype.Service;

@Service
public class FlashcardsService extends CrudService<FlashcardsRepository, Flashcard>{

    public FlashcardsService(FlashcardsRepository repository) {
        super(repository);
    }
}
