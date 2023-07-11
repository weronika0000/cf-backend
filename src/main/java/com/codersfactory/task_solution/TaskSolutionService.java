package com.codersfactory.task_solution;


import com.codersfactory.task_solution.dto.CreateTaskSolutionRequestDto;
import com.codersfactory.task_solution.dto.TaskSolutionResponseDto;

public interface TaskSolutionService {

    TaskSolutionResponseDto createTaskSolution (CreateTaskSolutionRequestDto createTaskSolutionRequestDto);

    TaskSolutionResponseDto getTaskSolutionById(Long taskSolutionId);
}
