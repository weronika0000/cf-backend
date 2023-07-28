package com.codersfactory.task_solution;

import com.codersfactory.task.exception.TaskNotFoundException;
import com.codersfactory.common.exception.UserNotAuthorizedException;
import com.codersfactory.task.Task;
import com.codersfactory.task.TaskRepository;
import com.codersfactory.task_solution.dto.CreateTaskSolutionRequestDto;
import com.codersfactory.task_solution.dto.TaskSolutionResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class TaskSolutionServiceImpl implements TaskSolutionService {
    private final TaskSolutionRepository taskSolutionRepository;
    private final TaskRepository taskRepository;
    private final TaskSolutionMapper mapper;

    @Override
    public TaskSolutionResponseDto createTaskSolution(CreateTaskSolutionRequestDto createTaskSolutionRequestDto) {
        Long taskId = createTaskSolutionRequestDto.taskId();
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        TaskSolution taskSolutionToSave = mapper.createTaskSolutionFromRequest(createTaskSolutionRequestDto, task);

        TaskSolution savedTaskSolution = taskSolutionRepository.save(taskSolutionToSave);

        return mapper.createResponseFromTaskSolution(savedTaskSolution);
    }

    @Override
    public TaskSolutionResponseDto getTaskSolutionById(Long taskSolutionId) {
        TaskSolution taskSolution = taskSolutionRepository
                .findById(taskSolutionId)
                .orElseThrow(() -> new TaskSolutionNotFoundException(taskSolutionId));

        return mapper.createResponseFromTaskSolution(taskSolution);
    }

    @Override
    public TaskSolutionResponseDto updateTaskSolutionById(Long taskSolutionId, CreateTaskSolutionRequestDto createTaskSolutionRequestDto) {
        TaskSolution taskSolution = taskSolutionRepository
                .findById(taskSolutionId)
                .orElseThrow(() -> new TaskSolutionNotFoundException(taskSolutionId));

        if (!createTaskSolutionRequestDto.userId().equals(taskSolution.getUserId())) {
            throw new UserNotAuthorizedException(createTaskSolutionRequestDto.userId());
        }

        if (!createTaskSolutionRequestDto.taskId().equals(taskSolution.getTask().getTaskId())) {
            throw new TaskNotFoundException(createTaskSolutionRequestDto.taskId());
        }

        taskSolution.setUserSolution(createTaskSolutionRequestDto.userSolution());

        TaskSolution savedTaskSolution = taskSolutionRepository.save(taskSolution);
        return mapper.createResponseFromTaskSolution(savedTaskSolution);
    }


}
