package com.codersfactory.control.service;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto);

//    List<Task> getAllTasks();
//
//    Optional<Task> getById (long taskId);
//
//    Optional<Task> updateTask (Task task);
//
//    void removeTask (long taskId);
//
//    Optional<Task> approveTask (Task task);

}
