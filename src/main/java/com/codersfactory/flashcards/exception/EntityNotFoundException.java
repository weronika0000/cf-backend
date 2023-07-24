package com.codersfactory.flashcards.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Class<?> entity, Long id) {
        super(entity.getSimpleName() + " entity with id " + id + " not found");
    }
}
