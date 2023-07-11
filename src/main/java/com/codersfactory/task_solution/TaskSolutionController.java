package com.codersfactory.task_solution;

import com.codersfactory.task_solution.dto.CreateTaskSolutionRequestDto;
import com.codersfactory.task_solution.dto.TaskSolutionResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks/solutions")
public class TaskSolutionController {
    private final TaskSolutionService taskSolutionService;

    @PostMapping
    public ResponseEntity<TaskSolutionResponseDto> createTaskSolution (@RequestBody CreateTaskSolutionRequestDto createTaskSolutionRequestDto){
        return ResponseEntity.ok(taskSolutionService.createTaskSolution(createTaskSolutionRequestDto));
    }


}
