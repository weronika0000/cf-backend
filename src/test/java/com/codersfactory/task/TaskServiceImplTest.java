package com.codersfactory.task;

import com.codersfactory.common.entity.DifficultyLevel;
import com.codersfactory.common.exception.TaskNotFoundException;
import com.codersfactory.common.exception.UserNotAuthorizedException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void testCreateTask_whenValidParametersProvided_thenReturnCreateTaskResponseDto() {
        //Arrange
        CreateTaskRequestDto CreateTaskRequestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Test technology", "Test tests");
        Task taskFromRequest = new Task(null, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests", false, null, null);
        Task taskFromDatabase = new Task(1L, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests", false, null, null);
        CreateTaskResponseDto taskResponseDto = new CreateTaskResponseDto(1L, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), "Test technology", "Test tests");

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
        Task taskFromDatabase = new Task(taskId, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests", false, null, null);
        TaskResponseDto taskResponseDto = new TaskResponseDto(taskId, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests", null, null);

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

    @Test
    void testUpdateTask_WhenValidParametersProvided_ThenReturnTaskResponseDto() {
        // Arrange
        Long taskId = 1L;
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto("Updated Title", "Updated Content", "Updated Example Solution", "Updated Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Updated technology", "Updated tests");
        Task taskFromDatabase = new Task(taskId, "Original Title", "Original Content", "Original Example Solution", "Original Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Original technology", "Original tests", false, null, null);
        TaskResponseDto expectedResponseDto = new TaskResponseDto(taskId, "Updated Title", "Updated Content", "Updated Example Solution", "Updated Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Updated technology", "Updated tests", null, null);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));
        taskFromDatabase.setTitle(taskDto.title());
        taskFromDatabase.setContent(taskDto.content());
        taskFromDatabase.setExampleSolution(taskDto.exampleSolution());
        taskFromDatabase.setHint(taskDto.hint());
        taskFromDatabase.setNumberOfPoints(taskDto.numberOfPoints());
        taskFromDatabase.setDifficultyLevel(taskDto.difficultyLevel());
        taskFromDatabase.setTechnology(taskDto.technology());
        taskFromDatabase.setTests(taskDto.tests());
        taskFromDatabase.setUpdatedAt(Instant.now());
        when(taskRepository.save(taskFromDatabase)).thenReturn(taskFromDatabase);
        when(taskMapper.responseDtoFromTask(taskFromDatabase)).thenReturn(expectedResponseDto);

        // Act
        TaskResponseDto result = taskService.updateTask(taskId, taskDto);

        // Assert
        assertEquals(expectedResponseDto, result);
    }

    @Test
    void testUpdateTask_WhenTaskDoesNotExist_ThenThrowException() {
        // Arrange
        Long taskId = 1L;
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto("Updated Title", "Updated Content", "Updated Example Solution", "Updated Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Updated technology", "Updated tests");
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskId, taskDto));
    }

    @Test
    void testUpdateTask_WhenUserNotAuthorized_ThenThrowException() {
        // Arrange
        Long taskId = 1L;
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto("Updated Title", "Updated Content", "Updated Example Solution", "Updated Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Updated technology", "Updated tests");
        Task taskFromDatabase = new Task(taskId, "Original Title", "Original Content", "Original Example Solution", "Original Hint", 5, DifficultyLevel.INTERMEDIATE, 2L, Instant.now(), Instant.now(), null, "Original technology", "Original tests", false, null, null);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));

        // Act & Assert
        assertThrows(UserNotAuthorizedException.class, () -> taskService.updateTask(taskId, taskDto));
    }

    @Test
    void testDeleteTaskById_whenValidParametersProvided() {
        // Arrange
        Long taskId = 1L;
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto("Updated Title", "Updated Content", "Updated Example Solution", "Updated Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Updated technology", "Updated tests");
        Task taskFromDatabase = new Task(taskId, "Original Title", "Original Content", "Original Example Solution", "Original Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Original technology", "Original tests", false, null, null);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));

        // Act
        taskService.deleteTaskById(taskId);

        // Assert
        verify(taskRepository, times(1)).delete(taskFromDatabase);
    }

    @Test
    void testDeleteTaskById_whenTaskNotFound_ThenThrowException() {
        // Arrange
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTaskById(taskId));
    }
}


