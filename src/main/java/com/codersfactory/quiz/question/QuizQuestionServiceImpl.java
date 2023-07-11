package com.codersfactory.quiz.question;


import com.codersfactory.quiz.question.dto.QuestionOptionDTO;
import com.codersfactory.quiz.question.dto.QuizQuestionDTO;
import com.codersfactory.quiz.question.dto.QuizQuestionUpdateDTO;
import com.codersfactory.quiz.question.exception.InvalidNumberOfOptionsException;
import com.codersfactory.quiz.question.exception.MissingCorrectAnswerException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Set;
@Service
@AllArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {


    private final QuizQuestionRepository quizQuestionRepository;

    private final QuizQuestionMapper quizQuestionMapper;


    @Override
    public QuizQuestionDTO createQuizQuestion(QuizQuestionDTO quizQuestionDTO) {
        QuestionType questionType = quizQuestionDTO.questionType();

        if (!hasCorrectAnswer(quizQuestionDTO)) {
            throw new MissingCorrectAnswerException();
        }

        if (!validateOptions(quizQuestionDTO)) {
            throw new InvalidNumberOfOptionsException(questionType.getMinOptions(), questionType.getMaxOptions());
        }


        Question question = quizQuestionMapper.toEntity(quizQuestionDTO);
        quizQuestionRepository.save(question);

        return quizQuestionMapper.toDto(question);
    }

    @Override
    public QuizQuestionDTO updateQuizQuestion(QuizQuestionUpdateDTO quizQuestionUpdateDTO) {
        return null;
    }


    @Override
    public QuizQuestionDTO getQuizQuestionById(Long id) {
        Question question
                = quizQuestionRepository
                .findById(id)
                .orElseThrow(() -> new QuizQuestionNotFoundException(id));


        return quizQuestionMapper.toDto(question);
    }

    @Override
    public void deleteQuizQuestion(Long id) {
        quizQuestionRepository.deleteById(id);
    }

//    private QuizQuestionDTO createMultipleChoiceQuizQuestion(QuizQuestionDTO quizQuestionDTO) {
//        Question question = quizQuestionMapper.toEntity(quizQuestionDTO);
//        quizQuestionRepository.save(question);
//
//        return quizQuestionMapper.toDto(question);
//    }
//
//    private QuizQuestionDTO createTypeInQuizQuestion(QuizQuestionDTO quizQuestionDTO) {
//        Question question = quizQuestionMapper.toEntity(quizQuestionDTO);
//        quizQuestionRepository.save(question);
//
//        return quizQuestionMapper.toDto(question);
//    }
//
//    private QuizQuestionDTO createSingleChoiceQuizQuestion(QuizQuestionDTO quizQuestionDTO) {
//        Question question = quizQuestionMapper.toEntity(quizQuestionDTO);
//        quizQuestionRepository.save(question);
//
//        return quizQuestionMapper.toDto(question);
//    }


    private boolean validateOptions(QuizQuestionDTO quizQUestionDTO) {
        QuestionType questionType = quizQUestionDTO.questionType();
        Set<QuestionOptionDTO> options = quizQUestionDTO.options();

        if (options.size() < questionType.getMinOptions() || options.size() > questionType.getMaxOptions()) {
            throw new InvalidNumberOfOptionsException(questionType.getMinOptions(), questionType.getMaxOptions());
        }

        return true;
    }


    private boolean hasCorrectAnswer(QuizQuestionDTO quizQuestionDTO) {
        return quizQuestionDTO.options()
                .stream()
                .anyMatch(QuestionOptionDTO::isCorrect);

    }

}
