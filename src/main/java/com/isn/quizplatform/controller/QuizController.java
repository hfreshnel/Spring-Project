package com.isn.quizplatform.controller;

import com.isn.quizplatform.model.ApiResponse;
import com.isn.quizplatform.model.Quiz;
import com.isn.quizplatform.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // Récupérer tous les quiz
    @GetMapping("public/quiz")
    public ResponseEntity<ApiResponse<List<Quiz>>> getAllQuiz() {
        ApiResponse<List<Quiz>> response = quizService.getAllQuiz();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // Récupérer les détails d'un quiz spécifique
    @GetMapping("public/quiz/{id}")
    public ResponseEntity<ApiResponse<Quiz>> getQuizById(@PathVariable Long id) {
        ApiResponse<Quiz> response = quizService.getQuizById(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/{id}/questionIds")
    public ResponseEntity<ApiResponse<List<Long>>> getMethodName(@PathVariable Long id) {
        ApiResponse<List<Long>> response = quizService.getQuestionIdsByQuizId(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    

    // Créer un nouveau quiz
    @PostMapping("admin/quiz")
    public ResponseEntity<ApiResponse<Quiz>> createQuiz(@RequestBody Quiz quiz) {
        ApiResponse<Quiz> response = quizService.createQuiz(quiz);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // Mettre à jour un quiz
    @PutMapping("admin/quiz/{id}")
    public ResponseEntity<ApiResponse<Quiz>> updateQuiz(@PathVariable Long id, @RequestBody Quiz quizDetails) {
        ApiResponse<Quiz> response = quizService.updateQuiz(id, quizDetails);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // Supprimer un quiz
    @DeleteMapping("admin/quiz/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteQuiz(@PathVariable Long id) {
        ApiResponse<Boolean> response = quizService.deleteQuiz(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    // Associer des questions à un quiz
    @PostMapping("admin/{quizId}/question/{questionId}")
    public ResponseEntity<ApiResponse<Quiz>> addQuestionToQuiz(
            @PathVariable Long quizId,
            @PathVariable Long questionId) {

        ApiResponse<Quiz> response = quizService.addQuestionToQuiz(quizId, questionId);
        return ResponseEntity.status(response.getCode()).body(response);
    }


}
   

