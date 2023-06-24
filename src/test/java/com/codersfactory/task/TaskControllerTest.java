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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @Mock
    private TaskServiceImpl taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void createTask_ValidRequest_ReturnsCreatedTask() {
        // Arrange
        CreateTaskRequestDto requestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Test technology", "Test tests");
        CreateTaskResponseDto responseDto = new CreateTaskResponseDto(1L, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), "Test technology", "Test tests");
        when(taskService.createTask(any(CreateTaskRequestDto.class))).thenReturn(responseDto);

        // Act
        ResponseEntity<CreateTaskResponseDto> response = taskController.createTask(requestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(taskService, times(1)).createTask(eq(requestDto));
    }

    @Test
    void getTask_ExistingId_ReturnsTask() {
        // Arrange
        Long taskId = 1L;
        TaskResponseDto responseDto = new TaskResponseDto(taskId, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests");
        when(taskService.getById(eq(taskId))).thenReturn(responseDto);

        // Act
        ResponseEntity<TaskResponseDto> response = taskController.getTask(taskId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(taskService, times(1)).getById(eq(taskId));
    }

    @Test
    void getTask_TaskNotFound_ReturnsNotFoundResponse() {
        // Arrange
        Long taskId = 1L;
        when(taskService.getById(taskId)).thenThrow(new TaskNotFoundException(taskId));

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> taskController.getTask(taskId));
        verify(taskService, times(1)).getById(taskId);
    }

    @Test
    void updateTask_ExistingId_ValidRequest_ReturnsUpdatedTask() {
        // Arrange
        Long taskId = 1L;
        CreateTaskRequestDto requestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Test technology", "Test tests");
        TaskResponseDto responseDto = new TaskResponseDto(taskId, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests");
        when(taskService.updateTask(eq(taskId), any(CreateTaskRequestDto.class))).thenReturn(responseDto);

        // Act
        ResponseEntity<TaskResponseDto> response = taskController.updateTask(taskId, requestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(taskService, times(1)).updateTask(eq(taskId), eq(requestDto));
    }

    @Test
    void updateTask_TaskNotFound_ReturnsTaskNotFoundException() {
        // Arrange
        Long taskId = 1L;
        CreateTaskRequestDto requestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Test technology", "Test tests");
        when(taskService.updateTask(taskId, requestDto)).thenThrow(new TaskNotFoundException(1L));

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> taskController.updateTask(taskId, requestDto));
        verify(taskService, times(1)).updateTask(taskId, requestDto);
    }

    @Test
    void updateTask_UserNotAuthorized_ReturnsForbiddenResponse() {
        // Arrange
        Long taskId = 1L;
        CreateTaskRequestDto requestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, DifficultyLevel.INTERMEDIATE, 1L, "Test technology", "Test tests");
        when(taskService.updateTask(taskId, requestDto)).thenThrow(new UserNotAuthorizedException(1L));

        // Act & Assert
        assertThrows(UserNotAuthorizedException.class, () -> taskController.updateTask(taskId, requestDto));
        verify(taskService, times(1)).updateTask(taskId, requestDto);
    }

    @Test
    void deleteTask_ExistingId_ReturnsOkResponse() {
        // Arrange
        Long taskId = 1L;

        // Act
        ResponseEntity<String> response = taskController.deleteTask(taskId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService, times(1)).deleteTaskById(eq(taskId));
    }

    @Test
    void deleteTask_TaskNotFound_ReturnsNotFoundResponse() {
        // Arrange
        Long taskId = 1L;
        doThrow(new TaskNotFoundException(taskId)).when(taskService).deleteTaskById(taskId);

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> taskController.deleteTask(taskId));
        verify(taskService, times(1)).deleteTaskById(taskId);
    }

}