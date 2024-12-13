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
@RequestMapping("/public/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // Récupérer tous les quiz
    @GetMapping
    public ResponseEntity<ApiResponse<List<Quiz>>> getAllQuiz() {
        ApiResponse<List<Quiz>> response = quizService.getAllQuiz();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // Récupérer les détails d'un quiz spécifique
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Quiz>> getQuizById(@PathVariable Long id) {
        ApiResponse<Quiz> response = quizService.getQuizById(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // Créer un nouveau quiz
    @PostMapping
    public ResponseEntity<ApiResponse<Quiz>> createQuiz(@RequestBody Quiz quiz) {
        ApiResponse<Quiz> response = quizService.createQuiz(quiz);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // Mettre à jour un quiz
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Quiz>> updateQuiz(@PathVariable Long id, @RequestBody Quiz quizDetails) {
        ApiResponse<Quiz> response = quizService.updateQuiz(id, quizDetails);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // Supprimer un quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteQuiz(@PathVariable Long id) {
        ApiResponse<Boolean> response = quizService.deleteQuiz(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
