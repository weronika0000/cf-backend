package com.codersfactory.task_assignment;

import com.codersfactory.task_assignment.dto.CreateTaskAssignmentRequest;
import com.codersfactory.task_assignment.dto.CreateTaskAssignmentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("/api/tasks/assignments")

public class TaskAssignmentController {
    private final TaskAssignmentService taskAssignmentService;

    @PostMapping
    public ResponseEntity<CreateTaskAssignmentResponse> createTaskAssignment
            (@RequestBody CreateTaskAssignmentRequest createTaskAssignmentRequest) {

        return ResponseEntity.ok(taskAssignmentService.createTaskAssignment(createTaskAssignmentRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateTaskAssignmentResponse> getTaskAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(taskAssignmentService.getTaskAssignmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateTaskAssignmentResponse> updateTaskAssignmentById
            (@PathVariable Long id,
             @RequestBody CreateTaskAssignmentRequest createTaskAssignmentRequest) {

        return ResponseEntity.ok(taskAssignmentService.updateTaskAssignmentById(id, createTaskAssignmentRequest));
    }
}
