package com.codersfactory.boundary.mapper;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.boundary.dto.TaskResponseDto;
import com.codersfactory.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring", imports = {Instant.class})

public interface TaskMapper {
    @Mapping (target = "createdAt", expression = "java(Instant.now())")
    @Mapping (target = "updatedAt", expression = "java(Instant.now())")
    Task createTaskFromRequest (CreateTaskRequestDto taskDto);
    CreateTaskResponseDto createResponseDtoFromTask(Task task);
    TaskResponseDto responseDtoFromTask(Task task);
}
