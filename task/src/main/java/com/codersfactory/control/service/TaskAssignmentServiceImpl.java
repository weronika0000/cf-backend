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
        Long userId = createTaskAssignmentRequest.userId();

        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));

        TaskAssignment taskAssignment =
                taskAssignmentRepository.findByTaskAndUserId(task, userId);

         if (taskAssignment != null) {
                throw new TaskAssignmentAlreadyExistsException
                        ("Task assignment with task id '" + taskId + "' and user id '" + userId + "' already exists");
          }

        TaskAssignment taskAssignmentToSave
                = mapper.createTaskAssignmentFromRequest(createTaskAssignmentRequest);
         taskAssignmentToSave.setTask(task);

        TaskAssignment savedTaskAssignment = taskAssignmentRepository.save(taskAssignmentToSave);

        return mapper.createResponseFromTaskAssignment(savedTaskAssignment);
    }
  
    @Override
    public CreateTaskAssignmentResponse getTaskAssignmentById(Long id) {
        TaskAssignment taskAssignment = taskAssignmentRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task assignment with id " + id + " not found"));

        return mapper.createResponseFromTaskAssignment(taskAssignment);
    }
}
