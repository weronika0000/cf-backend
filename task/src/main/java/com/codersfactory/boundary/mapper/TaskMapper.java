package com.codersfactory.boundary.mapper;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.boundary.dto.TaskResponseDto;
import com.codersfactory.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task createTaskFromRequest (CreateTaskRequestDto taskDto);
    CreateTaskResponseDto createResponseDtoFromTask(Task task);
    TaskResponseDto responseDtoFromTask(Task task);
}
