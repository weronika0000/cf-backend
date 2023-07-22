package com.codersfactory.task;

import com.codersfactory.common.entity.DifficultyLevel;
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

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import static com.codersfactory.common.entity.DifficultyLevel.INTERMEDIATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    CreateTaskRequestDto CreateTaskRequestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, INTERMEDIATE, 1L, "Test technology", "Test tests");
    private static final String TITLE= "Test Title";
    private static final String CONTENT  = "Test Content";
    private static final String EXAMPLE_SOLUTION = "Test Example Solution";
    private static final String HINT = "Test Hint";
    private static final int NUMBER_OF_POINTS = 5;
    private static final DifficultyLevel DIFFICULTY_LEVEL = INTERMEDIATE;
    private static final Long CREATOR_ID = 1L;
    private static final Instant CREATED_AT= Instant.now();

    private static final  Duration AVERAGE_COMPLETION_TIME = null;
    private static final String TECHNOLOGY ="Test technology";
    private static final String TESTS = "Test tests";
    private static final boolean IF_APPROVED = false;
    private static Long taskId = 1L;

     private static final CreateTaskRequestDto CREATE_TASK_REQUEST_DTO =
             new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, INTERMEDIATE, 1L, "Test technology", "Test tests");
    private static final CreateTaskResponseDto CREATE_TASK_RESPONSE_DTO =
            new CreateTaskResponseDto(1L, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, INTERMEDIATE, 1L, Instant.now(), "Test technology", "Test tests");
    private static final TaskResponseDto TASK_RESPONSE_DTO =
            new TaskResponseDto(1L, "Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Test technology", "Test tests", null, null);
    private static final CreateTaskRequestDto UPDATED_CREATE_TASK_REQUEST_DTO =
            new CreateTaskRequestDto("Updated Title", "Updated Content", "Updated Example Solution", "Updated Hint", 5, INTERMEDIATE, 1L, "Updated technology", "Updated tests");
    private TaskResponseDto UPDATED_TASK_RESPONSE_DTO =
            new TaskResponseDto(taskId, "Updated Title", "Updated Content", "Updated Example Solution", "Updated Hint", 5, INTERMEDIATE, 1L, Instant.now(), Instant.now(), null, "Updated technology", "Updated tests", null, null);


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

       // CreateTaskRequestDto createTaskRequestDto = createTestTaskRequestDto();
        Task taskFromRequest = createTestTask();
        Task taskFromDatabase = createTestTask();
        taskFromDatabase.setTaskId(1L);
      //  CreateTaskResponseDto taskResponseDto = createTestTaskResponseDto();

        when(taskMapper.createTaskFromRequest(CREATE_TASK_REQUEST_DTO)).thenReturn(taskFromRequest);
        when(taskRepository.save(taskFromRequest)).thenReturn(taskFromDatabase);
        when(taskMapper.createResponseDtoFromTask(taskFromDatabase)).thenReturn(CREATE_TASK_RESPONSE_DTO);

        //Act
        CreateTaskResponseDto result = taskService.createTask(CREATE_TASK_REQUEST_DTO);

        //Assert
        assertEquals(CREATE_TASK_RESPONSE_DTO, result);
    }

    @DisplayName("test getById: when task exists, then return taskResponseDto")
    @Test
    void testGetById_WhenTaskExists_ThenReturnTaskResponseDto() {
        // given
        Task taskFromDatabase = createTestTask();
        taskFromDatabase.setTaskId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));
        when(taskMapper.responseDtoFromTask(taskFromDatabase)).thenReturn(TASK_RESPONSE_DTO);

        // when
        TaskResponseDto result = taskService.getById(taskId);

        // then
        assertEquals(TASK_RESPONSE_DTO, result);
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
        Task taskFromDatabase = createTestTask();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));
        taskFromDatabase.setTitle(UPDATED_CREATE_TASK_REQUEST_DTO.title());
        taskFromDatabase.setContent(UPDATED_CREATE_TASK_REQUEST_DTO.content());
        taskFromDatabase.setExampleSolution(UPDATED_CREATE_TASK_REQUEST_DTO.exampleSolution());
        taskFromDatabase.setHint(UPDATED_CREATE_TASK_REQUEST_DTO.hint());
        taskFromDatabase.setNumberOfPoints(UPDATED_CREATE_TASK_REQUEST_DTO.numberOfPoints());
        taskFromDatabase.setDifficultyLevel(UPDATED_CREATE_TASK_REQUEST_DTO.difficultyLevel());
        taskFromDatabase.setTechnology(UPDATED_CREATE_TASK_REQUEST_DTO.technology());
        taskFromDatabase.setTests(UPDATED_CREATE_TASK_REQUEST_DTO.tests());
        taskFromDatabase.setUpdatedAt(Instant.now());
        when(taskRepository.save(taskFromDatabase)).thenReturn(taskFromDatabase);
        when(taskMapper.responseDtoFromTask(taskFromDatabase)).thenReturn(UPDATED_TASK_RESPONSE_DTO);

        // when
        TaskResponseDto result = taskService.updateTask(taskId, UPDATED_CREATE_TASK_REQUEST_DTO);

        // then
        assertEquals(UPDATED_TASK_RESPONSE_DTO, result);
    }
    @DisplayName("test updateTask: when task does not exist, then throw TaskNotFoundException")
    @Test
    void testUpdateTask_WhenTaskDoesNotExist_ThenThrowException() {
        // given

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // when & Assert
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskId, UPDATED_CREATE_TASK_REQUEST_DTO));
    }
    @DisplayName("test updateTask: when user not authorized, then throw UserNotAuthorizedException")
    @Test
    void testUpdateTask_WhenUserNotAuthorized_ThenThrowException() {
        // given
        Task taskFromDatabase = createTestTask();
        taskFromDatabase.setCreatorId(2L);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskFromDatabase));

        // when & Assert
        assertThrows(UserNotAuthorizedException.class, () -> taskService.updateTask(taskId, UPDATED_CREATE_TASK_REQUEST_DTO));
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




    private Task createTestTask() {
        return Task.builder()
                .taskId(null)
                .title(TITLE)
                .content(CONTENT)
                .exampleSolution(EXAMPLE_SOLUTION)
                .hint(HINT)
                .numberOfPoints(NUMBER_OF_POINTS)
                .difficultyLevel(INTERMEDIATE)
                .creatorId(CREATOR_ID)
                .createdAt(Instant.now())
                .averageCompletionTime(AVERAGE_COMPLETION_TIME)
                .technology(TECHNOLOGY)
                .tests(TESTS)
                .ifApproved(IF_APPROVED)
                .build();

    }

}


