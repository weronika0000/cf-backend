package com.codersfactory.task_solution;

public class TaskSolutionNotFoundException extends RuntimeException {
    public TaskSolutionNotFoundException(Long taskSolutionId) {super("Solution with id '" + taskSolutionId + "' not found");}
}
