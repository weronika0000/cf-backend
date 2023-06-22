package com.codersfactory.control.service;

import com.codersfactory.boundary.dto.CreateTaskAssignmentRequest;
import com.codersfactory.boundary.dto.CreateTaskAssignmentResponse;
import com.codersfactory.boundary.mapper.TaskAssignmentMapper;
import com.codersfactory.control.exceptions.TaskAssignmentAlreadyExistsException;
import com.codersfactory.control.exceptions.TaskNotFoundException;
import com.codersfactory.control.repository.TaskAssignmentRepository;
import com.codersfactory.control.repository.TaskRepository;
import com.codersfactory.entity.Task;
import com.codersfactory.entity.TaskAssignment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskAssignmentServiceImpl implements TaskAssignmentService{

    private final TaskAssignmentRepository taskAssignmentRepository;

    private final TaskRepository taskRepository;

    private final TaskAssignmentMapper mapper;
    @Override
    public CreateTaskAssignmentResponse createTaskAssignment
            (CreateTaskAssignmentRequest createTaskAssignmentRequest) {

        Long taskId = createTaskAssignmentRequest.taskId();

        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        Long userId = createTaskAssignmentRequest.userId();

        TaskAssignment taskAssignment =
                taskAssignmentRepository.findByTaskAndUserId(task, userId);

        if (taskAssignment != null) {
                throw new TaskAssignmentAlreadyExistsException
                        (taskId, userId);
          }

        TaskAssignment taskAssignmentToSave
                = mapper.createTaskAssignmentFromRequest(createTaskAssignmentRequest, task);

        TaskAssignment savedTaskAssignment = taskAssignmentRepository.save(taskAssignmentToSave);

        return mapper.createResponseFromTaskAssignment(savedTaskAssignment);
    }
  
    @Override
    public CreateTaskAssignmentResponse getTaskAssignmentById(Long taskId) {
        TaskAssignment taskAssignment = taskAssignmentRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        return mapper.createResponseFromTaskAssignment(taskAssignment);
    }
}
