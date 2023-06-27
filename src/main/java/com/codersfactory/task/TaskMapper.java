package com.codersfactory.task;

import com.codersfactory.task.dto.CreateTaskRequestDto;
import com.codersfactory.task.dto.CreateTaskResponseDto;
import com.codersfactory.task.dto.TaskResponseDto;
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
