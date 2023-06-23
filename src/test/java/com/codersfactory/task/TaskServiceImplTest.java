package com.codersfactory.task;

import com.codersfactory.common.entity.DifficultyLevel;
import com.codersfactory.common.exception.TaskNotFoundException;
import com.codersfactory.task.dto.CreateTaskRequestDto;
import com.codersfactory.task.dto.CreateTaskResponseDto;
import com.codersfactory.task.dto.TaskResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void testCreateTask_whenValidParametersProvided_thenReturnCreateTaskResponseDto(){
        //Arrange
        CreateTaskRequestDto CreateTaskRequestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Test technology", "Test tests");
        Task taskFromRequest = new Task(null, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests", false, null);
        Task taskFromDatabase = new Task(1L, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests", false, null);
        CreateTaskResponseDto taskResponseDto = new CreateTaskResponseDto(1L, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(),  "Test technology", "Test tests");

        when(taskMapper.createTaskFromRequest(CreateTaskRequestDto)).thenReturn(taskFromRequest);
        when(taskRepository.save(taskFromRequest)).thenReturn(taskFromDatabase);
        when(taskMapper.createResponseDtoFromTask(taskFromDatabase)).thenReturn(taskResponseDto);

        //Act
        CreateTaskResponseDto result = taskService.createTask(CreateTaskRequestDto);

        //Assert
        assertEquals(taskResponseDto, result);
    }

    @Test
    void testGetById_WhenTaskExists_ThenReturnTaskResponseDto() {
        // Arrange
        Long taskId = 1L;
        Task taskFromDatabase = new Task(taskId, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests", false, null);
        TaskResponseDto taskResponseDto = new TaskResponseDto(taskId, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(),  null,"Test technology", "Test tests");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));
        when(taskMapper.responseDtoFromTask(taskFromDatabase)).thenReturn(taskResponseDto);

        // Act
        TaskResponseDto result = taskService.getById(taskId);

        // Assert
        assertEquals(taskResponseDto, result);
    }

    @Test
    void testGetById_WhenTaskDoesNotExist_ThenThrowException() {
        // Arrange
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> taskService.getById(taskId));
    }

}