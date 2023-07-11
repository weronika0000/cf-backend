package com.codersfactory.task;

import com.codersfactory.task.dto.CreateTaskRequestDto;
import com.codersfactory.task.dto.CreateTaskResponseDto;
import com.codersfactory.task.dto.TaskResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task createTaskFromRequest (CreateTaskRequestDto taskDto);
    CreateTaskResponseDto createResponseDtoFromTask(Task task);
    TaskResponseDto responseDtoFromTask(Task task);
}
