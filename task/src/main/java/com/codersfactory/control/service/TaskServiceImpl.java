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
        CreateTaskResponseDto responseTaskDto = createResponseDtoFromTask(responseTask);
        return responseTaskDto;
    }

    //    //TODO
//    @Override
//    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }
    @Override
    public TaskResponseDto getById(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> {
            throw new RuntimeException("The task does not exist");
        });
        TaskResponseDto taskResponseDto = responseDtoFromTask(task);
        return taskResponseDto;
    }
//
//    //TODO
//    @Override
//    public Optional<Task> updateTask(Task task) {
//        return Optional.empty();
//    }
//
//    //TODO
//    @Override
//    public void removeTask(long taskId) {
//
//    }
//
//    //TODO
//    @Override
//    public Optional<Task> approveTask(Task task) {
//        return Optional.empty();
//    }
}
