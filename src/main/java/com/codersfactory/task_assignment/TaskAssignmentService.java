package com.codersfactory.task_assignment;


import com.codersfactory.task_assignment.dto.CreateTaskAssignmentRequest;
import com.codersfactory.task_assignment.dto.CreateTaskAssignmentResponse;
import org.springframework.stereotype.Service;

@Service
public interface TaskAssignmentService {
    CreateTaskAssignmentResponse createTaskAssignment
            (CreateTaskAssignmentRequest createTaskAssignmentRequest);

    CreateTaskAssignmentResponse getTaskAssignmentById(Long id);

    CreateTaskAssignmentResponse updateTaskAssignmentById
            (Long id, CreateTaskAssignmentRequest createTaskAssignmentRequest);
}
