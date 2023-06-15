package com.codersfactory.boundary.controller;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
import com.codersfactory.boundary.dto.TaskResponseDto;
import com.codersfactory.control.service.TaskServiceImpl;
import com.codersfactory.entity.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.codersfactory.boundary.mapper.TaskMapper.createTaskFromRequest;

@RestController
@RequestMapping("/api/tasks")
//@Validated
public class TaskController {
    private TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<CreateTaskResponseDto> createTask(@RequestBody @Valid CreateTaskRequestDto taskDto){
        CreateTaskResponseDto createdTask = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask (@PathVariable Long id){
        TaskResponseDto retrievedTask= taskService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(retrievedTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @Valid @RequestBody CreateTaskRequestDto taskDto) {
        TaskResponseDto updatedTask = taskService.updateTask(id, taskDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }

}
