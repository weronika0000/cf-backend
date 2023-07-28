package com.codersfactory.task_assignment.exception;

public class TaskAssignmentAlreadyExistsException extends RuntimeException {

    public TaskAssignmentAlreadyExistsException(Long taskId, Long userId) {
        super("Task assignment with task id '" + taskId + "' and user id '" + userId + "' already exists");
    }
}
