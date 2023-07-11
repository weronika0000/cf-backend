package com.codersfactory.task_solution;

import com.codersfactory.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskSolutionRepository extends JpaRepository<TaskSolution, Long> {
    Optional<TaskSolution> findByTaskAndUserId(Task task, Long userId);
}
