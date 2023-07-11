package com.codersfactory.task_assignment;

import com.codersfactory.common.exception.TaskAssignmentAlreadyExistsException;
import com.codersfactory.common.exception.TaskAssignmentNotFoundException;
import com.codersfactory.common.exception.TaskNotFoundException;
import com.codersfactory.task.TaskRepository;
import com.codersfactory.task.Task;
import com.codersfactory.task_assignment.dto.CreateTaskAssignmentRequest;
import com.codersfactory.task_assignment.dto.CreateTaskAssignmentResponse;
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

    @Override
    public CreateTaskAssignmentResponse updateTaskAssignmentById
            (Long id,
             CreateTaskAssignmentRequest createTaskAssignmentRequest) {

        TaskAssignment taskAssignment = taskAssignmentRepository
                .findById(id)
                .orElseThrow(() -> new TaskAssignmentNotFoundException(id));

        Task task = taskRepository
                .findById(createTaskAssignmentRequest.taskId())
                .orElseThrow(() -> new TaskNotFoundException(createTaskAssignmentRequest.taskId()));

        taskAssignment.setTask(task);

        TaskAssignment savedTaskAssignment = taskAssignmentRepository.save(taskAssignment);

        return mapper.createResponseFromTaskAssignment(savedTaskAssignment);
    }
}
