package com.codersfactory.control.exceptions;

public class TaskAssignmentAlreadyExistsException extends RuntimeException{
    public TaskAssignmentAlreadyExistsException(String message) {
        super(message);
    }
}
