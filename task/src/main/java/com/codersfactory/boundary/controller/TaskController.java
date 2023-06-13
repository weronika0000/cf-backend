package com.codersfactory.boundary.controller;

import com.codersfactory.boundary.dto.CreateTaskRequestDto;
import com.codersfactory.boundary.dto.CreateTaskResponseDto;
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

//    @PutMapping("/{id}")
//    public void updateTask(@PathVariable Long id, @Valid  @RequestBody TaskRequestDto taskDto){
//    }

//    @GetMapping
//    public List<TaskResponseDto> getTasks(){
//        return null;
//    }

}
