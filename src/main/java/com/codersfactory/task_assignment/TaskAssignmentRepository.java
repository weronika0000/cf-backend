package com.codersfactory.task_assignment;

import com.codersfactory.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    TaskAssignment findByTaskAndUserId(Task task, Long userId);
}
