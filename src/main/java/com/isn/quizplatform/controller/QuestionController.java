package com.isn.quizplatform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*")
@RestController
@EnableWebMvc
@RequestMapping("/")
public class QuestionController {
	
	 private final QuestionService questionService;
	 
	    public QuestionController(QuestionService questionService) {
	        this.questionService = questionService;
	    }
	

	
	/**
	 * 	Méthode permettant de recupérer toutes les questions pour un Quiz donné
	 * @param quizId
	 * @return
	 */
	    @GetMapping("public/questions")
	    public ResponseEntity<ApiResponse<List<Question>>> getAllQuestions() {
	        ApiResponse<List<Question>> response = questionService.getAllQuestions();
	        return ResponseEntity.status(response.getCode()).body(response);
	    }
	 
	 /***
	  * Méthode permettant de recuperer une question spécifique
	  * @param questionId
	  * @return
	  */
	    @GetMapping("public/questions/{questionId}")
	    public ResponseEntity<ApiResponse<Question>> getQuestionById(@PathVariable Long questionId) {
	        ApiResponse<Question> response = questionService.getQuestionById(questionId);
	        return ResponseEntity.status(response.getCode()).body(response);
	    }
	 
	 /**
	  * Méthode permettant de créer une question
	  * @param newQuestion
	  * @return
	  */
	    @PostMapping("admin/questions")
	    public ResponseEntity<ApiResponse<Question>> createQuestion(@RequestBody Question newQuestion) {
	        ApiResponse<Question> response = questionService.createQuestion(newQuestion);
	        return ResponseEntity.status(response.getCode()).body(response);
	    }

	 
	 /**
	  * Méthode permettant de modifier une question
	  * @param questionId
	  * @param updatedQuestion
	  * @return
	  */
	    @PutMapping("admin/questions/{questionId}")
	    public ResponseEntity<ApiResponse<Question>> updateQuestion(
	            @PathVariable Long questionId, @RequestBody Question updatedQuestion) {
	        updatedQuestion.setId(questionId);
	        ApiResponse<Question> response = questionService.updateQuestion(updatedQuestion);
	        return ResponseEntity.status(response.getCode()).body(response);
	    }

	 /**
	  * Méthode permettant de supprimer une question
	  * @param questionId
	  * @return
	  */
	    @DeleteMapping("admin/questions/{questionId}")
	    public ResponseEntity<ApiResponse<Boolean>> deleteQuestion(@PathVariable Long questionId) {
	        ApiResponse<Boolean> response = questionService.deleteQuestion(questionId);
	        return ResponseEntity.status(response.getCode()).body(response);
	    }

}
