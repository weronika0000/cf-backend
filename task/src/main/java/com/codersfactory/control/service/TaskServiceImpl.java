package com.codersfactory.control.service;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.control.repository.TaskRepository;
import com.codersfactory.entity.Task;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.codersfactory.boundary.mapper.TaskMapper.createResponseDtoFromTask;
import static com.codersfactory.boundary.mapper.TaskMapper.createTaskFromRequest;

@Service
public class TaskServiceImpl implements TaskService{

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
//    //TODO
//    @Override
//    public Optional<Task> getById(long taskId) {
//        return taskRepository.findById(taskId);
//    }
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
