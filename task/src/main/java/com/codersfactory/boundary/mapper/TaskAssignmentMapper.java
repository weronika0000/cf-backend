package com.codersfactory.boundary.mapper;

import com.codersfactory.boundary.dto.CreateTaskAssignmentRequest;
import com.codersfactory.boundary.dto.CreateTaskAssignmentResponse;
import com.codersfactory.entity.Task;
import com.codersfactory.entity.TaskAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface TaskAssignmentMapper {

    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "updatedAt", expression = "java(Instant.now())")

    TaskAssignment createTaskAssignmentFromRequest(CreateTaskAssignmentRequest taskAssignment);

    @Mapping (target = "taskId", source = "task.taskId")
    CreateTaskAssignmentResponse createResponseFromTaskAssignment(TaskAssignment taskAssignment);

}
