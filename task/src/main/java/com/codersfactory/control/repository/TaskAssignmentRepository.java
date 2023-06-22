package com.codersfactory.control.repository;

import com.codersfactory.entity.Task;
import com.codersfactory.entity.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    TaskAssignment findByTaskAndUserId(Task task, Long userId);
}
