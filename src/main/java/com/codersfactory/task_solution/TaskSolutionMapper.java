package com.codersfactory.task_solution;

import com.codersfactory.task.Task;
import com.codersfactory.task_solution.dto.CreateTaskSolutionRequestDto;
import com.codersfactory.task_solution.dto.TaskSolutionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskSolutionMapper {

    @Mapping(target= "task", source= "task")
    @Mapping (target = "isCorrect", expression = "java(computeIsCorrect())")
    @Mapping(target = "pointsReceived", expression = "java(computePointsReceived(task, computeIsCorrect()))")
    TaskSolution createTaskSolutionFromRequest(CreateTaskSolutionRequestDto createTaskSolutionRequestDto, Task task);

    @Mapping (target = "taskId", source = "task.taskId")
    TaskSolutionResponseDto createResponseFromTaskSolution(TaskSolution taskSolution);

    default int computePointsReceived(Task task, boolean isCorrect) {
        return isCorrect ? task.getNumberOfPoints() : 0;
    }

    default boolean computeIsCorrect() {
        return new java.util.Random().nextBoolean();
    }

}
