package com.codersfactory.boundary.controller;

import com.codersfactory.boundary.dto.CreateTaskAssignmentRequest;
import com.codersfactory.boundary.dto.CreateTaskAssignmentResponse;
import com.codersfactory.control.service.TaskAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
