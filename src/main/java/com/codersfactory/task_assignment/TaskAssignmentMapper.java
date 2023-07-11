package com.codersfactory.task_assignment;

import com.codersfactory.task.Task;
import com.codersfactory.task_assignment.dto.CreateTaskAssignmentRequest;
import com.codersfactory.task_assignment.dto.CreateTaskAssignmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskAssignmentMapper {

    TaskAssignment createTaskAssignmentFromRequest(CreateTaskAssignmentRequest taskAssignment, Task task);

    @Mapping (target = "taskId", source = "task.taskId")
    CreateTaskAssignmentResponse createResponseFromTaskAssignment(TaskAssignment taskAssignment);

}
