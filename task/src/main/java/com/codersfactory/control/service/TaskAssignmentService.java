package com.codersfactory.control.service;


import com.codersfactory.boundary.dto.CreateTaskAssignmentRequest;
import com.codersfactory.boundary.dto.CreateTaskAssignmentResponse;
import org.springframework.stereotype.Service;

@Service
public interface TaskAssignmentService {
    CreateTaskAssignmentResponse createTaskAssignment
            (CreateTaskAssignmentRequest createTaskAssignmentRequest);

    CreateTaskAssignmentResponse getTaskAssignmentById(Long id);

    CreateTaskAssignmentResponse updateTaskAssignmentById
            (Long id, CreateTaskAssignmentRequest createTaskAssignmentRequest);
}
