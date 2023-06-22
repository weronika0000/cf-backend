package com.codersfactory.task;

import com.codersfactory.task.dto.CreateTaskRequestDto;
import com.codersfactory.task.dto.CreateTaskResponseDto;
import com.codersfactory.task.dto.TaskResponseDto;

public interface TaskService {

    CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto);
    TaskResponseDto getById(Long taskId);
    TaskResponseDto updateTask (Long taskId, CreateTaskRequestDto taskDto);
    void deleteTaskById(Long taskId);
}
