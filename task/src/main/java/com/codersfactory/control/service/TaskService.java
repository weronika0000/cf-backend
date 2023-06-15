package com.codersfactory.control.service;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.boundary.dto.TaskResponseDto;
import com.codersfactory.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto);
    TaskResponseDto getById(Long taskId);
    TaskResponseDto updateTask (Long taskId, CreateTaskRequestDto taskDto);


}
