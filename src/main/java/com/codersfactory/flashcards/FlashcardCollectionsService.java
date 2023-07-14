package com.codersfactory.flashcards;

import org.springframework.stereotype.Service;

@Service
public class FlashcardCollectionsService extends CrudService<FlashcardCollectionsRepository, FlashcardCollection>{
    protected FlashcardCollectionsService(FlashcardCollectionsRepository repository) {
        super(repository);
    }
}
