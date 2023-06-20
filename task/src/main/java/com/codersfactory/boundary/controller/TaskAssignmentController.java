package com.codersfactory.boundary.controller;

import com.codersfactory.boundary.dto.CreateTaskAssignmentRequest;
import com.codersfactory.boundary.dto.CreateTaskAssignmentResponse;
import com.codersfactory.control.service.TaskAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
