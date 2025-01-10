package com.isn.quizplatform.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
 
import com.isn.quizplatform.model.ApiResponse;
import com.isn.quizplatform.service.ChoisirService;
 
import java.util.List;
import java.util.Map;
 
@CrossOrigin(origins = "*")
@RestController
public class ChoisirController {
 
	@Autowired
	private ChoisirService chosirService;
	@GetMapping("/public/quiz/{quiz_id}/classement")
    public ApiResponse<List<Map<String, Object>>> getClassement(@PathVariable Long quiz_id) {
        return new ApiResponse<List<Map<String, Object>>>(chosirService.getClassementByQuizId(quiz_id),200,null);
    }
	 @GetMapping("/public/quiz/{quiz_id}/questions/{question_id}/")
	    public ApiResponse<List<Map<String, Object>>> getPropositionsPercentage(@PathVariable Long question_id,@PathVariable Long quiz_id) {
	        System.out.println("Controller: Received request for /propositions-percentage with questionID: " + question_id + " and quizID: " + quiz_id);
	    	return new ApiResponse<List<Map<String, Object>>>(chosirService.getPropositionsPercentage(question_id,quiz_id),200,null);
	 }
	 @GetMapping("/public/quiz/{quiz_id}/proposition/{proposition_id}/user/{user_id}")
	    public ApiResponse<String> selectPro(@PathVariable Long quiz_id,@PathVariable Long proposition_id,@PathVariable Long user_id) {
	        System.out.println("Controller: Received request for /propositions-percentage with questionID: " + proposition_id + " and quizID: " + quiz_id);
	    	return new ApiResponse<String>(chosirService.addProposition(quiz_id,proposition_id,user_id),200,null);
	 }
}
