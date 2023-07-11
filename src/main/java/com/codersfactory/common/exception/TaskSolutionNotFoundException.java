package com.codersfactory.common.exception;

public class TaskSolutionNotFoundException extends RuntimeException {
    public TaskSolutionNotFoundException(Long taskSolutionId) {super("Solution with id '" + taskSolutionId + "' not found");}
}
