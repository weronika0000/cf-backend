package com.codersfactory.task_solution;

import com.codersfactory.task_solution.dto.CreateTaskSolutionRequestDto;
import com.codersfactory.task_solution.dto.TaskSolutionResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks/solutions")
public class TaskSolutionController {
    private final TaskSolutionService taskSolutionService;

    @PostMapping
    public ResponseEntity<TaskSolutionResponseDto> createTaskSolution
            (@RequestBody CreateTaskSolutionRequestDto createTaskSolutionRequestDto){
        return ResponseEntity.ok(taskSolutionService.createTaskSolution(createTaskSolutionRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskSolutionResponseDto> getTaskSolutionById (@PathVariable Long id){
        return ResponseEntity.ok(taskSolutionService.getTaskSolutionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskSolutionResponseDto> updateTaskSolutionById
            (@PathVariable Long id,
             @RequestBody CreateTaskSolutionRequestDto createTaskSolutionRequestDto){
        return ResponseEntity.ok(taskSolutionService.updateTaskSolutionById(id, createTaskSolutionRequestDto));
    }


}
