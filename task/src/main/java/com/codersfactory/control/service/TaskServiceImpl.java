package com.codersfactory.control.service;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.boundary.dto.TaskResponseDto;
import com.codersfactory.boundary.mapper.TaskMapper;
import com.codersfactory.control.exceptions.TaskNotFoundException;
import com.codersfactory.control.exceptions.UserNotAuthorizedException;
import com.codersfactory.control.repository.TaskRepository;
import com.codersfactory.entity.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto) {
        Task recievedTask = taskMapper.createTaskFromRequest(taskDto);

        Task responseTask = taskRepository.save(recievedTask);

        return taskMapper.createResponseDtoFromTask(responseTask);
    }

    @Override
    public TaskResponseDto getById(Long taskId) {
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException(taskId));

        return taskMapper.responseDtoFromTask(task);
    }

    @Override
    public TaskResponseDto updateTask(Long taskId, CreateTaskRequestDto taskDto) {
        Task taskFromDatabase = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException(taskId));

        if (!taskDto.creatorId().equals(taskFromDatabase.getCreatorId())){
            throw new UserNotAuthorizedException(taskDto.creatorId());
        }
        updateTaskFromDto(taskFromDatabase, taskDto);

        Task responseTask = taskRepository.save(taskFromDatabase);

        return taskMapper.responseDtoFromTask(responseTask);
    }

    @Override
    public void deleteTaskById(Long taskId) {
        Task taskFromDatabase = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        taskRepository.delete(taskFromDatabase);
    }

    private void updateTaskFromDto(Task taskToUpdate, CreateTaskRequestDto taskDto) {
        taskToUpdate.setTitle(taskDto.title());
        taskToUpdate.setContent(taskDto.content());
        taskToUpdate.setExampleSolution(taskDto.exampleSolution());
        taskToUpdate.setHint(taskDto.hint());
        taskToUpdate.setNumberOfPoints(taskDto.numberOfPoints());
        taskToUpdate.setDifficultyLevel(taskDto.difficultyLevel());
        taskToUpdate.setTechnology(taskDto.technology());
        taskToUpdate.setTests(taskDto.tests());
        taskToUpdate.setUpdatedAt(Instant.now());
    }

}
