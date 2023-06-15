package com.codersfactory.control.service;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.boundary.dto.TaskResponseDto;
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
                        new RuntimeException("The task does not exist"));

        return responseDtoFromTask(task);
    }

    @Override
    public TaskResponseDto updateTask(Long taskId, CreateTaskRequestDto taskDto) {
        Task taskFromDatabase = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new RuntimeException("The task does not exist"));

        Task receivedTask = createTaskFromRequest(taskDto);
        receivedTask.setUpdatedAt(Instant.now());
        receivedTask.setAverageCompletionTime(taskFromDatabase.getAverageCompletionTime());
        receivedTask.setTaskId(taskFromDatabase.getTaskId());
        receivedTask.setIfApproved(taskFromDatabase.isIfApproved());
        Task responseTask = taskRepository.save(receivedTask);

        return responseDtoFromTask(responseTask);
    }
}
