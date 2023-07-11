package com.codersfactory.common.exception;

public class TaskAssignmentNotFoundException extends RuntimeException{
    public TaskAssignmentNotFoundException(Long taskAssignmentId) {super("Task assignment with id '" + taskAssignmentId + "' not found");}
}
