package com.codersfactory.control.service;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.boundary.dto.TaskResponseDto;
import com.codersfactory.control.exceptions.TaskNotFoundException;
import com.codersfactory.control.repository.TaskRepository;
import com.codersfactory.entity.Task;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static com.codersfactory.boundary.mapper.TaskMapper.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto) {
        Task recievedTask = createTaskFromRequest(taskDto);
        recievedTask.setCreatedAt(Instant.now());
        Task responseTask = taskRepository.save(recievedTask);

        return createResponseDtoFromTask(responseTask);
    }

    @Override
    public TaskResponseDto getById(Long taskId) {
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException("The task does not exist"));

        return responseDtoFromTask(task);
    }

    @Override
    public TaskResponseDto updateTask(Long taskId, CreateTaskRequestDto taskDto) {
        Task taskFromDatabase = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException("The task does not exist"));

        if (!taskDto.creatorId().equals(taskFromDatabase.getCreatorId())){
            throw new RuntimeException("This user is not allowed to update this task.");
        }

       taskFromDatabase.setTitle(taskDto.title());
       taskFromDatabase.setContent(taskDto.content());
       taskFromDatabase.setExampleSolution(taskDto.exampleSolution());
       taskFromDatabase.setHint(taskDto.hint());
       taskFromDatabase.setNumberOfPoints(taskDto.numberOfPoints());
       taskFromDatabase.setDifficultyLevel(taskDto.difficultyLevel());
       taskFromDatabase.setTechnology(taskDto.technology());
       taskFromDatabase.setTests(taskDto.tests());
       taskFromDatabase.setUpdatedAt(Instant.now());

        Task responseTask = taskRepository.save(taskFromDatabase);

        return responseDtoFromTask(responseTask);
    }

    @Override
    public void deleteTaskById(Long taskId) {
        Task taskFromDatabase = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("The task does not exist"));

        taskRepository.delete(taskFromDatabase);
    }
}
