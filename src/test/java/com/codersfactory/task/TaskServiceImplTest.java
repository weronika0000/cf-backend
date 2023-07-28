package com.codersfactory.task;

import com.codersfactory.article.Article;
import com.codersfactory.common.entity.DifficultyLevel;
import com.codersfactory.task.exception.TaskNotFoundException;
import com.codersfactory.common.exception.UserNotAuthorizedException;
import com.codersfactory.task.dto.CreateTaskRequestDto;
import com.codersfactory.task.dto.CreateTaskResponseDto;
import com.codersfactory.task.dto.TaskResponseDto;
import com.codersfactory.task_solution.TaskSolution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.codersfactory.common.entity.DifficultyLevel.INTERMEDIATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    CreateTaskRequestDto CreateTaskRequestDto = new CreateTaskRequestDto("Test Title", "Test Content", "Test Example Solution", "Test Hint", 5, INTERMEDIATE, 1L, "Test technology", "Test tests");
    private static Long taskId = 1L;
    private static final String TITLE= "Test Title";
    private static final String UPDATED_TITLE = "Updated Title";
    private static final String CONTENT  = "Test Content";
    private static final String EXAMPLE_SOLUTION = "Test Example Solution";
    private static final String HINT = "Test Hint";
    private static final int NUMBER_OF_POINTS = 5;
    private static final DifficultyLevel DIFFICULTY_LEVEL = INTERMEDIATE;
    private static final Long CREATOR_ID = 1L;
    private static final Instant CREATED_AT= Instant.now();
    private static final Instant UPDATED_AT= Instant.now();
    private static final  Duration AVERAGE_COMPLETION_TIME = null;
    private static final String TECHNOLOGY ="Test technology";
    private static final String TESTS = "Test tests";
    private static final boolean IF_APPROVED = false;
    private static final Set<TaskSolution> TASK_SOLUTION = null;
    private static final Article ARTICLE_ID = null;

    private static final CreateTaskRequestDto CREATE_TASK_REQUEST_DTO =
            new CreateTaskRequestDto(TITLE, CONTENT, EXAMPLE_SOLUTION, HINT, NUMBER_OF_POINTS, DIFFICULTY_LEVEL, CREATOR_ID, TECHNOLOGY, TESTS);
    private static final CreateTaskResponseDto CREATE_TASK_RESPONSE_DTO =
            new CreateTaskResponseDto(CREATOR_ID, TITLE, CONTENT, EXAMPLE_SOLUTION, HINT, NUMBER_OF_POINTS, DIFFICULTY_LEVEL, CREATOR_ID, CREATED_AT, TECHNOLOGY, TESTS);
    private static final TaskResponseDto TASK_RESPONSE_DTO =
            new TaskResponseDto(CREATOR_ID, TITLE, CONTENT, EXAMPLE_SOLUTION, HINT, NUMBER_OF_POINTS, DIFFICULTY_LEVEL, CREATOR_ID, CREATED_AT, UPDATED_AT, AVERAGE_COMPLETION_TIME, TESTS, TESTS, TASK_SOLUTION, ARTICLE_ID);
    private static final CreateTaskRequestDto UPDATED_CREATE_TASK_REQUEST_DTO =
            new CreateTaskRequestDto(UPDATED_TITLE, CONTENT, EXAMPLE_SOLUTION, HINT, NUMBER_OF_POINTS, DIFFICULTY_LEVEL, CREATOR_ID, TECHNOLOGY, TESTS);
    private TaskResponseDto UPDATED_TASK_RESPONSE_DTO =
            new TaskResponseDto(CREATOR_ID, UPDATED_TITLE, CONTENT, EXAMPLE_SOLUTION, HINT, NUMBER_OF_POINTS, DIFFICULTY_LEVEL, CREATOR_ID, CREATED_AT, UPDATED_AT, AVERAGE_COMPLETION_TIME, TESTS, TESTS, TASK_SOLUTION, ARTICLE_ID);


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
        Task taskFromRequest = createTestTask();
        Task taskFromDatabase = createTestTask();
        taskFromDatabase.setTaskId(1L);

        given(taskMapper.createTaskFromRequest(CREATE_TASK_REQUEST_DTO)).willReturn(taskFromRequest);
        given(taskRepository.save(taskFromRequest)).willReturn(taskFromDatabase);
        given(taskMapper.createResponseDtoFromTask(taskFromDatabase)).willReturn(CREATE_TASK_RESPONSE_DTO);

        //when
        CreateTaskResponseDto result = taskService.createTask(CREATE_TASK_REQUEST_DTO);

        //then
        assertEquals(CREATE_TASK_RESPONSE_DTO, result);
    }

    @DisplayName("test getById: when task exists, then return taskResponseDto")
    @Test
    void testGetById_WhenTaskExists_ThenReturnTaskResponseDto() {
        // given
        Task taskFromDatabase = createTestTask();
        taskFromDatabase.setTaskId(taskId);

        given(taskRepository.findById(taskId)).willReturn(Optional.of(taskFromDatabase));
        given(taskMapper.responseDtoFromTask(taskFromDatabase)).willReturn(TASK_RESPONSE_DTO);

        // when
        TaskResponseDto result = taskService.getById(taskId);

        // then
        assertEquals(TASK_RESPONSE_DTO, result);
    }

    @DisplayName("test getById: when task exists, then throw TaskNotFoundException")
    @Test
    void testGetById_WhenTaskDoesNotExist_ThenThrowException() {
        // given
        given(taskRepository.findById(taskId)).willReturn(Optional.empty());
        // when & then
        assertThrows(TaskNotFoundException.class, () -> taskService.getById(taskId));
    }

    @DisplayName("test updateTask: when valid parameters provided, then return updated taskResponseDto")
    @Test
    void testUpdateTask_WhenValidParametersProvided_ThenReturnTaskResponseDto() {
        // given
        Task taskFromDatabase = createTestTask();
        given(taskRepository.findById(taskId)).willReturn(Optional.of(taskFromDatabase));
        taskFromDatabase.setTitle(UPDATED_CREATE_TASK_REQUEST_DTO.title());
        taskFromDatabase.setUpdatedAt(Instant.now());
        given(taskRepository.save(taskFromDatabase)).willReturn(taskFromDatabase);
        given(taskMapper.responseDtoFromTask(taskFromDatabase)).willReturn(UPDATED_TASK_RESPONSE_DTO);

        // when
        TaskResponseDto result = taskService.updateTask(taskId, UPDATED_CREATE_TASK_REQUEST_DTO);

        // then
        assertEquals(UPDATED_TASK_RESPONSE_DTO, result);
    }
    @DisplayName("test updateTask: when task does not exist, then throw TaskNotFoundException")
    @Test
    void testUpdateTask_WhenTaskDoesNotExist_ThenThrowException() {
        // given
        given(taskRepository.findById(taskId)).willReturn(Optional.empty());

        // when & then
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(taskId, UPDATED_CREATE_TASK_REQUEST_DTO));
    }
    @DisplayName("test updateTask: when user not authorized, then throw UserNotAuthorizedException")
    @Test
    void testUpdateTask_WhenUserNotAuthorized_ThenThrowException() {
        // given
        Task taskFromDatabase = createTestTask();
        taskFromDatabase.setCreatorId(2L);

        given(taskRepository.findById(taskId)).willReturn(Optional.of(taskFromDatabase));

        // when & Assert
        assertThrows(UserNotAuthorizedException.class, () -> taskService.updateTask(taskId, UPDATED_CREATE_TASK_REQUEST_DTO));
    }
    @DisplayName("test deleteTaskById: when valid parameters provided, then delete task")
    @Test
    void testDeleteTaskById_whenValidParametersProvided() {
        // given
        Task taskFromDatabase = createTestTask();
        given(taskRepository.findById(taskId)).willReturn(Optional.of(taskFromDatabase));

        // when
        taskService.deleteTaskById(taskId);
        // then
        verify(taskRepository, times(1)).delete(taskFromDatabase);
    }
    @DisplayName("test deleteTaskById: when task not found, then throw TaskNotFoundException")
    @Test
    void testDeleteTaskById_whenTaskNotFound_ThenThrowException() {
        // given
        given(taskRepository.findById(taskId)).willReturn(Optional.empty());
        // when & then
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


