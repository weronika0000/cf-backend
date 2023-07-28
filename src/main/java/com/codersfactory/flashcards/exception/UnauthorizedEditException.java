package com.codersfactory.flashcards.exception;

public class UnauthorizedEditException extends RuntimeException {
    public UnauthorizedEditException(Class<?> entity, UnauthorizedAction action) {
        super("User not permitted to do action: " + action + " on " + entity.getSimpleName());
    }
}
