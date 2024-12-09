package com.isn.quizplatform.controller;

import com.isn.quizplatform.model.Quiz;
// import com.isn.quizplatform.model.Question;
import com.isn.quizplatform.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
 
    @Autowired
    private QuizService quizService;

    // Récupérer tous les quiz
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuiz() {
        List<Quiz> quizList = quizService.getAllQuiz();
        return ResponseEntity.ok(quizList);
    }

    // Récupérer les détails d'un quiz spécifique
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quiz);
    }

    // Créer un nouveau quiz
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz newQuiz = quizService.createQuiz(quiz);
        return ResponseEntity.ok(newQuiz);
    }

    // Mettre à jour un quiz
    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz quizDetails) {
        Quiz updatedQuiz = quizService.updateQuiz(id, quizDetails);
        if (updatedQuiz == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedQuiz);
    }

    // Supprimer un quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        boolean isDeleted = quizService.deleteQuiz(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    /* 
    // Récupérer toutes les questions d'un quiz
    @GetMapping("/{id}/questions")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable Long id) {
        List<Question> questions = quizService.getQuestionsByQuiz(id);
        if (questions == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questions);
    }

    // Ajouter une nouvelle question à un quiz
    @PostMapping("/{id}/question")
    public ResponseEntity<Question> addQuestionToQuiz(@PathVariable Long id, @RequestBody Question question) {
        Question newQuestion = quizService.addQuestionToQuiz(id, question);
        if (newQuestion == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newQuestion);
    }
*/

/* 
    // Obtenir les statistiques d’un quiz
    @GetMapping("/{id}/stats")
    public ResponseEntity<Object> getQuizStats(@PathVariable Long id) {
        Object stats = quizService.getQuizStats(id);
        if (stats == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stats);
    }
*/
    // Mettre à jour l’étape actuelle d’un quiz
    @PutMapping("/{id}/etape")
    public ResponseEntity<Quiz> updateEtape(@PathVariable Long id, @RequestParam int etape) {
        Quiz updatedQuiz = quizService.updateEtape(id, etape);
        if (updatedQuiz == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedQuiz);
    }
}
