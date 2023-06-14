package com.codersfactory.boundary.mapper;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.boundary.dto.TaskResponseDto;
import com.codersfactory.entity.Task;

public class TaskMapper {
    public static Task createTaskFromRequest(CreateTaskRequestDto taskDto) {
        return Task.builder()
                .content(taskDto.content())
                .title(taskDto.title())
                .exampleSolution(taskDto.exampleSolution())
                .hint(taskDto.hint())
                .numberOfPoints(taskDto.numberOfPoints())
                .difficultyLevel(taskDto.difficultyLevel())
                .creatorId(taskDto.creatorId())
                .technology(taskDto.technology())
                .tests(taskDto.tests())
                .build();
    }

    public static CreateTaskResponseDto createResponseDtoFromTask(Task task) {
        return CreateTaskResponseDto.builder()
                .taskId(task.getTaskId())
                .content(task.getContent())
                .title(task.getTitle())
                .exampleSolution(task.getExampleSolution())
                .hint(task.getHint())
                .numberOfPoints(task.getNumberOfPoints())
                .difficultyLevel(task.getDifficultyLevel())
                .creatorId(task.getCreatorId())
                .createdAt(task.getCreatedAt())
                .technology(task.getTechnology())
                .tests(task.getTests())
                .build();
    }


    public static TaskResponseDto responseDtoFromTask(Task task) {
        return TaskResponseDto.builder()
                .taskId(task.getTaskId())
                .content(task.getContent())
                .title(task.getTitle())
                .exampleSolution(task.getExampleSolution())
                .hint(task.getHint())
                .numberOfPoints(task.getNumberOfPoints())
                .difficultyLevel(task.getDifficultyLevel())
                .creatorId(task.getCreatorId())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .averageCompletionTime(task.getAverageCompletionTime())
                .technology(task.getTechnology())
                .tests(task.getTests())
                .build();
    }


}
