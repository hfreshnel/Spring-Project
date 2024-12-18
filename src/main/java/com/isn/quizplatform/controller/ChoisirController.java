package com.isn.quizplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isn.quizplatform.model.ApiResponse;
import com.isn.quizplatform.service.ChoisirService;

import java.util.List;
import java.util.Map;

@RestController
public class ChoisirController {

	@Autowired
	private ChoisirService chosirService;
	
	@GetMapping("/public/quiz/{quiz_id}/classement")
    public ApiResponse<List<Map<String, Object>>> getClassement(@RequestParam("quiz_id") Long quizID) {
        return new ApiResponse<List<Map<String, Object>>>(chosirService.getClassementByQuizId(quizID),200,null);
    }
	
	 @GetMapping("/public/quiz/{quiz_id}/questions/{question_id}/")
	    public ApiResponse<List<Map<String, Object>>> getPropositionsPercentage(@RequestParam("question_id") Long questionID,@RequestParam("quizID") Long quizID) {
	        System.out.println("Controller: Received request for /propositions-percentage with questionID: " + questionID + " and quizID: " + quizID);
	    	
	    	return new ApiResponse<List<Map<String, Object>>>(chosirService.getPropositionsPercentage(questionID,quizID),200,null);
	 }
	 
	 @GetMapping("/public/quiz/{quiz_id}/proposition/{proposition_id}/user/{user_id}")
	    public ApiResponse<String> selectPro(@RequestParam("quizID") Long quizID,@RequestParam("proposition_id") Long proposition_id,@RequestParam("user_id") Long user_id) {
	        System.out.println("Controller: Received request for /propositions-percentage with questionID: " + proposition_id + " and quizID: " + quizID);
	    	
	    	return new ApiResponse<String>(chosirService.addProposition(quizID,proposition_id,user_id),200,null);
	 }
}
