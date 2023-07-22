package com.codersfactory.task;

import com.codersfactory.task.exception.TaskNotFoundException;
import com.codersfactory.common.exception.UserNotAuthorizedException;
import com.codersfactory.task.dto.CreateTaskRequestDto;
import com.codersfactory.task.dto.CreateTaskResponseDto;
import com.codersfactory.task.dto.TaskResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static com.codersfactory.common.entity.DifficultyLevel.INTERMEDIATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
//    CreateTaskRequestDto CreateTaskRequestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, INTERMEDIATE, 1L, "Test technology", "Test tests");
//    private static final String title = "Test Title";
//    private static final String content = "Test Content";
//    private static final String exampleSolution = "Test Example Solution";
//    private static final String hint = "Test Hint";
//
//    private static final int numberOfPoints = 5;
//
//    private static final DifficultyLevel difficultyLevel = INTERMEDIATE;
//    private static final Long creatorID = 1L;
//
//    private static final String technology ="Test technology";
//    private static final String tests = "Test tests";

    private static final Long taskId = 1L;
    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @DisplayName("test createTask: when parameters are valid should return taskResponseDto")
    @Test
    void testCreateTask_whenValidParametersProvided_thenReturnCreateTaskResponseDto() {
        //given

        CreateTaskRequestDto createTaskRequestDto = createTestTaskRequestDto();
        Task taskFromRequest = createTestTask();
        Task taskFromDatabase = createTestTask();
        taskFromDatabase.setTaskId(1L);
        CreateTaskResponseDto taskResponseDto = createTestTaskResponseDto();

        when(taskMapper.createTaskFromRequest(createTaskRequestDto)).thenReturn(taskFromRequest);
        when(taskRepository.save(taskFromRequest)).thenReturn(taskFromDatabase);
        when(taskMapper.createResponseDtoFromTask(taskFromDatabase)).thenReturn(taskResponseDto);

        //Act
        CreateTaskResponseDto result = taskService.createTask(createTaskRequestDto);

        //Assert
        assertEquals(taskResponseDto, result);
    }

    @DisplayName("test getById: when task exists, then return taskResponseDto")
    @Test
    void testGetById_WhenTaskExists_ThenReturnTaskResponseDto() {
        // given
        Task taskFromDatabase = createTestTask();
        taskFromDatabase.setTaskId(taskId);
        TaskResponseDto taskResponseDto = testTaskResponseDto();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));
        when(taskMapper.responseDtoFromTask(taskFromDatabase)).thenReturn(taskResponseDto);

        // when
        TaskResponseDto result = taskService.getById(taskId);

        // then
        assertEquals(taskResponseDto, result);
    }

    @DisplayName("test getById: when task exists, then throw TaskNotFoundException")
    @Test
    void testGetById_WhenTaskDoesNotExist_ThenThrowException() {
        // given
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // when & Assert
        assertThrows(TaskNotFoundException.class, () -> taskService.getById(taskId));
    }

    @DisplayName("test updateTask: when valid parameters provided, then return updated taskResponseDto")
    @Test
    void testUpdateTask_WhenValidParametersProvided_ThenReturnTaskResponseDto() {
        // given
        CreateTaskRequestDto taskDto = updateTestTaskRequestDto();
        Task taskFromDatabase = createTestTask();
        TaskResponseDto expectedResponseDto = updateTestTaskResponseDto();

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

        // when
        TaskResponseDto result = taskService.updateTask(taskId, taskDto);

        // then
        assertEquals(expectedResponseDto, result);
    }
    @DisplayName("test updateTask: when task does not exist, then throw TaskNotFoundException")
    @Test
    void testUpdateTask_WhenTaskDoesNotExist_ThenThrowException() {
        // given
        CreateTaskRequestDto taskDto = updateTestTaskRequestDto();

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // when & Assert
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskId, taskDto));
    }
    @DisplayName("test updateTask: when user not authorized, then throw UserNotAuthorizedException")
    @Test
    void testUpdateTask_WhenUserNotAuthorized_ThenThrowException() {
        // given
        CreateTaskRequestDto taskDto = updateTestTaskRequestDto();
        Task taskFromDatabase = createTestTask();
        taskFromDatabase.setCreatorId(2L);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));

        // when & Assert
        assertThrows(UserNotAuthorizedException.class, () -> taskService.updateTask(taskId, taskDto));
    }
    @DisplayName("test deleteTaskById: when valid parameters provided, then delete task")
    @Test
    void testDeleteTaskById_whenValidParametersProvided() {
        // given
        Task taskFromDatabase = createTestTask();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));

        // when
        taskService.deleteTaskById(taskId);

        // then
        verify(taskRepository, times(1)).delete(taskFromDatabase);
    }
    @DisplayName("test deleteTaskById: when task not found, then throw TaskNotFoundException")
    @Test
    void testDeleteTaskById_whenTaskNotFound_ThenThrowException() {
        // given
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // when & Assert
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTaskById(taskId));
    }


    private CreateTaskRequestDto createTestTaskRequestDto() {
        CreateTaskRequestDto createTaskRequestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, INTERMEDIATE, 1L, "Test technology", "Test tests");
        return createTaskRequestDto;
    }

    private Task createTestTask() {
        return Task.builder()
                .taskId(null)
                .title("Test Title")
                .content("Test Content")
                .exampleSolution("Test Example Solution")
                .hint("Test Hint")
                .numberOfPoints(5)
                .difficultyLevel(INTERMEDIATE)
                .creatorId(1L)
                .createdAt(Instant.now())
                .averageCompletionTime(null)
                .technology("Test technology")
                .tests("Test tests")
                .ifApproved(false)
                .build();

    }

    private CreateTaskResponseDto createTestTaskResponseDto() {
        CreateTaskResponseDto createTaskResponseDto = new CreateTaskResponseDto(1L, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, INTERMEDIATE, 1L, Instant.now(), "Test technology", "Test tests");
        return createTaskResponseDto;
    }


    private TaskResponseDto testTaskResponseDto() {
        TaskResponseDto taskResponseDto = new TaskResponseDto(1L, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests", null, null);
        return taskResponseDto;
    }


    private CreateTaskRequestDto updateTestTaskRequestDto() {
        CreateTaskRequestDto updateTaskRequestDto = new CreateTaskRequestDto("Updated Title", "Updated Content", "Updated Example Solution", "Updated Hint", 5, INTERMEDIATE, 1L, "Updated technology", "Updated tests");
        return updateTaskRequestDto;
    }

    private TaskResponseDto updateTestTaskResponseDto() {
        TaskResponseDto expectedResponseDto = new TaskResponseDto(taskId, "Updated Title", "Updated Content", "Updated Example Solution", "Updated Hint", 5, INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Updated technology", "Updated tests", null, null);
        return expectedResponseDto;
    }

}


