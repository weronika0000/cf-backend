package com.codersfactory.quiz.question;

import com.codersfactory.quiz.question.dto.QuizQuestionDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/quiz-question")
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;

    @PostMapping
    public ResponseEntity<QuizQuestionDTO> createQuizQuestion(@RequestBody QuizQuestionDTO quizQuestionDTO) {
        return ResponseEntity.ok(quizQuestionService.createQuizQuestion(quizQuestionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizQuestionDTO> getQuizQuestion(@PathVariable Long id) {
        QuizQuestionDTO quizQuestion = quizQuestionService.getQuizQuestionById(id);
        return ResponseEntity.ok(quizQuestion);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<QuizQuestionDTO> updateQuizQuestion(@PathVariable Long id, @RequestBody QuizQuestionUpdateDTO quizQuestionUpdateDTO) {
//        QuizQuestionDTO updatedQuizQuestion = quizQuestionService.updateQuizQuestion(id, quizQuestionUpdateDTO);
//        return ResponseEntity.ok(updatedQuizQuestion);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizQuestion(@PathVariable Long id) {
        quizQuestionService.deleteQuizQuestion(id);
        return ResponseEntity.noContent().build();
    }

}
