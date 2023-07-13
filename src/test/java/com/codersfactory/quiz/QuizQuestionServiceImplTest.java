//package com.codersfactory.quiz;
//
//
//import com.codersfactory.quiz.question.dto.QuestionOptionDTO;
//import com.codersfactory.quiz.question.dto.QuizQuestionDTO;
//import com.codersfactory.quiz.question.exception.InvalidNumberOfOptionsException;
//import com.codersfactory.quiz.question.*;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.Instant;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.Mockito.*;
//
//
//@Slf4j
//@ExtendWith(MockitoExtension.class)
//class QuizQuestionServiceImplTest {
//
//    private static final Long ID_1L = 1L;
//
//    private static final Long ID_2L = 2L;
//
//    private static final Instant TIME_NOW = Instant.now();
//
//
//    private static final String QUESTION_TEXT_1 = "What is the capital of France?";
//
//    private static final String QUESTION_TEXT_2 = "What is the capital of Germany?";
//
//    private static final String QUESTION_OPTION_TEXT_1 = "Paris";
//
//    private static final String QUESTION_OPTION_TEXT_2 = "London";
//
//    private static final String QUESTION_OPTION_TEXT_3 = "Berlin";
//
//    private static final String QUESTION_OPTION_TEXT_4 = "Madrid";
//
//    private static final String QUESTION_OPTION_TEXT_5 = "Rome";
//
//    private static final String QUESTION_OPTION_TEXT_6 = "Warsaw";
//
//    private static final String ADDITIONAL_INFO_1 = "Paris is the capital of France";
//    @Mock
//    private QuizQuestionRepository quizQuestionRepository;
//
//    @Mock
//    private QuizQuestionMapper quizQuestionMapper;
//
//    @InjectMocks
//    private QuizQuestionServiceImpl quizQuestionServiceImpl;
//
//    //     void whenGetUserById_thenUserNotFoundExceptionShouldBeThrown() {
//    @DisplayName("Should create a single choice question")
//    @Test
//    void whenCreateQuizQuestionWithSingleChoiceType_thenQuizQuestionShouldBeCreated() {
//        // given
//        QuizQuestionDTO quizQuestionDTO = createQuizQuestionDTO();
//
//        Question question = createSingleChoiceQuestion();
//        question.setAnswers(createFourQuestionOption(question));
//
//        when(quizQuestionMapper.toEntity(quizQuestionDTO)).thenReturn(question);
//        when(quizQuestionRepository.save(question)).thenReturn(question);
//        when(quizQuestionMapper.toDto(question)).thenReturn(quizQuestionDTO);
//
//        // when
//        QuizQuestionDTO createdQuizQuestionDTO = quizQuestionServiceImpl.createQuizQuestion(quizQuestionDTO);
//
//        // then
//
//        log.info("createdQuizQuestionDTO: {}", createdQuizQuestionDTO);
//
//        verify(quizQuestionRepository, times(1)).save(question);
//        verify(quizQuestionMapper, times(1)).toDto(question);
//        assertThat(createdQuizQuestionDTO, equalTo(quizQuestionDTO));
//    }
//
//    @DisplayName
//            ("Should throw InvalidNumberOfOptionsException " +
//                    "when creating a single choice question with less than four options")
//    @Test
//    void whenCreateQuizQuestionWithSingleChoiceTypeAndLessThanFourOptions_thenInvalidNumberOfOptionsExceptionShouldBeThrown() {
//
//        // given
//        QuizQuestionDTO quizQuestionDTO = createSingleChoiceQuestionDTOWithTwoOptions();
//
//        Question question = createSingleChoiceQuestionWithTwoOptions();
//        question.setAnswers(createTwoQuestionOption(question));
//
//
//        // when & then
//        assertThrows
//                (InvalidNumberOfOptionsException.class,
//                        () -> quizQuestionServiceImpl.createQuizQuestion(quizQuestionDTO));
//    }
//
//    @DisplayName("Should create a multiple choice question")
//    @Test
//    void whenCreateQuizQuestionWithMultipleChoiceType_thenQuizQuestionShouldBeCreated() {
//        // given
//        QuizQuestionDTO quizQuestionDTO = createQuizQuestionDTO();
//
//        Question question = createMultipleChoiceQuestion();
//        question.setAnswers(createFourQuestionOption(question));
//
//        when(quizQuestionMapper.toEntity(quizQuestionDTO)).thenReturn(question);
//        when(quizQuestionRepository.save(question)).thenReturn(question);
//        when(quizQuestionMapper.toDto(question)).thenReturn(quizQuestionDTO);
//
//        // when
//        QuizQuestionDTO createdQuizQuestionDTO = quizQuestionServiceImpl.createQuizQuestion(quizQuestionDTO);
//
//        // then
//
//        log.info("createdQuizQuestionDTO: {}", createdQuizQuestionDTO);
//
//        verify(quizQuestionRepository, times(1)).save(question);
//        verify(quizQuestionMapper, times(1)).toDto(question);
//        assertThat(createdQuizQuestionDTO, equalTo(quizQuestionDTO));
//    }
//
////    @DisplayName
////            ("Should throw MissingCorrectAnswerException when creating a multiple choice question ")
////    @Test
////    public void whenCreateQuizQuestionWithMultipleChoiceTypeAndNoCorrectAnswer_thenMissingCorrectAnswerExceptionShouldBeThrown() {
////        // given
////
////
////
////
////        Question question = createMultipleChoiceQuestion();
////        question.setAnswers(createFourQuestionOption(question));
////
////
////        // when & then
////        assertThrows
////                (MissingCorrectAnswerException.class,
////                        () -> quizQuestionServiceImpl.createQuizQuestion(quizQuestionDTO));
//
////    }
//
//
//
//    private Question createMultipleChoiceQuestion() {
//
//        Question question = new Question();
//
//        question.setId(ID_1L);
//        question.setQuestionText(QUESTION_TEXT_1);
//        question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
//        question.setAnswers(createFourQuestionOption(question));
//
//        return question;
//    }
//
//
//    private QuizQuestionDTO createSingleChoiceQuestionDTOWithTwoOptions() {
//        return QuizQuestionDTO.builder()
//                .id(ID_1L)
//                .questionText(QUESTION_TEXT_1)
//                .additionalInfo(ADDITIONAL_INFO_1)
//                .questionType(QuestionType.SINGLE_CHOICE)
//                .options(createTwoQuestionOptionDTO())
//                .build();
//    }
//
//
//    private Question createSingleChoiceQuestionWithTwoOptions() {
//
//        Question question = new Question();
//
//        question.setId(ID_1L);
//        question.setQuestionText(QUESTION_TEXT_1);
//        question.setQuestionType(QuestionType.SINGLE_CHOICE);
//        question.setAnswers(createTwoQuestionOption(question));
//
//        return question;
//    }
//
//    private Set<QuestionOptionDTO> createTwoQuestionOptionDTO() {
//        return Set.of(
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_1, true),
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_2, false)
//        );
//    }
//
//    private Set<QuestionOption> createTwoQuestionOption(Question question) {
//        return Set.of(
//                new QuestionOption(ID_1L, QUESTION_OPTION_TEXT_1, true, question),
//                new QuestionOption(ID_2L, QUESTION_OPTION_TEXT_2, false, question)
//        );
//    }
//
//
//    private QuizQuestionDTO createQuizQuestionDTO() {
//        return QuizQuestionDTO.builder()
//                .id(ID_1L)
//                .questionText(QUESTION_TEXT_1)
//                .additionalInfo(ADDITIONAL_INFO_1)
//                .questionType(QuestionType.SINGLE_CHOICE)
//                .options(createFourQuestionOptionDTO())
//                .build();
//    }
//
//    private Question createSingleChoiceQuestion() {
//
//        Question question = new Question();
//
//        question.setId(ID_1L);
//        question.setQuestionText(QUESTION_TEXT_1);
//        question.setQuestionType(QuestionType.SINGLE_CHOICE);
//        question.setAdditionalInfo(ADDITIONAL_INFO_1);
//        question.setCreatedAt(TIME_NOW);
//        question.setUpdatedAt(TIME_NOW);
//
//        return question;
//    }
//
//    private Set<QuestionOption> createFourQuestionOption(Question question) {
//        return Set.of(
//                new QuestionOption(ID_1L, QUESTION_OPTION_TEXT_1, true, question),
//                new QuestionOption(ID_2L, QUESTION_OPTION_TEXT_2, false, question),
//                new QuestionOption(ID_1L, QUESTION_OPTION_TEXT_3, false, question),
//                new QuestionOption(ID_2L, QUESTION_OPTION_TEXT_4, false, question)
//        );
//    }
//
//    private Set<QuestionOptionDTO> createFourQuestionOptionDTO() {
//        return Set.of(
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_1, true),
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_2, false),
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_3, false),
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_4, false)
//        );
//    }
//
//    private Set<QuestionOptionDTO> createFourFalseQuestionOptionDTO() {
//        return Set.of(
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_1, false),
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_2, false),
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_3, false),
//                new QuestionOptionDTO(QUESTION_OPTION_TEXT_4, false)
//        );
//    }
//
//    private Set<QuestionOption> createFourFalseQuestionOption(Question question) {
//        return Set.of(
//                new QuestionOption(ID_1L, QUESTION_OPTION_TEXT_1, false, question),
//                new QuestionOption(ID_2L, QUESTION_OPTION_TEXT_2, false, question),
//                new QuestionOption(ID_1L, QUESTION_OPTION_TEXT_3, false, question),
//                new QuestionOption(ID_2L, QUESTION_OPTION_TEXT_4, false, question)
//        );
//    }
//
//
//}