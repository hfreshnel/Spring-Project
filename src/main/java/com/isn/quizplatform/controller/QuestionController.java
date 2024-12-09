package com.isn.quizplatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.isn.quizplatform.model.*;
import com.isn.quizplatform.service.QuestionService;

@RestController
@EnableWebMvc
@RequestMapping("/Question")
public class QuestionController {
	
	 @Autowired
	    private QuestionService questionService;
	
	public QuestionController() {
		
}
	
	/**
	 * 	Méthode permettant de recupérer toutes les questions pour un Quiz donné
	 * @param quizId
	 * @return
	 */
	 @GetMapping("/public/quiz/{quizId}/questions")
	    public ResponseEntity<List<Question>> getAllQuestions(@PathVariable Long quizId) {
	        List<Question> questions = questionService.getAllQuestionsByQuizId(quizId);
	        return ResponseEntity.ok(questions);
	    }
	 
	 /***
	  * Méthode permettant de recuperer une question spécifique
	  * @param questionId
	  * @return
	  */
	 @GetMapping("/public/questions/{questionId}")
	    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {
	        Optional<Question> questionOptional = questionService.getQuestionById(questionId);
	        return questionOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }
	 
	 /**
	  * Méthode permettant de créer une question
	  * @param newQuestion
	  * @return
	  */
	 @PostMapping("/admin/questions")
	    public ResponseEntity<Question> createQuestion(@RequestBody Question newQuestion) {
	        Question createdQuestion = questionService.createQuestion(newQuestion);
	        return ResponseEntity.ok(createdQuestion);
	    }

	 
	 /**
	  * Méthode permettant de modifier une question
	  * @param questionId
	  * @param updatedQuestion
	  * @return
	  */
	 @PutMapping("/admin/questions/{questionId}")
	    public ResponseEntity<Question> updateQuestion(
	            @PathVariable Long questionId, @RequestBody Question updatedQuestion) {
	        Optional<Question> updated = questionService.updateQuestion(questionId, updatedQuestion);
	        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }
	 
	 /**
	  * Méthode permettant de supprimer une question
	  * @param questionId
	  * @return
	  */
	 @DeleteMapping("/admin/questions/{questionId}")
	    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
	        boolean deleted = questionService.deleteQuestion(questionId);
	        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	    }
	

}
