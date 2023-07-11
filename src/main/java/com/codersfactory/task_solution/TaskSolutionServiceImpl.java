package com.codersfactory.task_solution;

import com.codersfactory.common.exception.TaskNotFoundException;
import com.codersfactory.task.Task;
import com.codersfactory.task.TaskRepository;
import com.codersfactory.task_solution.dto.CreateTaskSolutionRequestDto;
import com.codersfactory.task_solution.dto.TaskSolutionResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@AllArgsConstructor
@Service
public class TaskSolutionServiceImpl implements TaskSolutionService{
    private final TaskSolutionRepository taskSolutionRepository;
    private final TaskRepository taskRepository;
    private final TaskSolutionMapper mapper;

    @Override
    public TaskSolutionResponseDto createTaskSolution(CreateTaskSolutionRequestDto createTaskSolutionRequestDto) {
        Long taskId = createTaskSolutionRequestDto.taskId();
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        Long userId = createTaskSolutionRequestDto.userId();

        TaskSolution taskSolutionToSave = mapper.createTaskSolutionFromRequest(createTaskSolutionRequestDto, task);

        //if (taskSolutionToSave.)

        TaskSolution taskSolutionFromDatabase = taskSolutionRepository.save(taskSolutionToSave);

        return mapper.createResponseFromTaskAssignment(taskSolutionFromDatabase);
    }

    private boolean generateRandomBoolean(){
        Random random = new Random();
        return random.nextBoolean();
    }

}
