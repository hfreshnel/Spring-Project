package com.isn.quizplatform.controller;

import java.util.List;


import com.isn.quizplatform.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.isn.quizplatform.model.LoginRequest;
import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.service.AuthService;
import com.isn.quizplatform.service.PersonneService;

import jakarta.servlet.http.HttpSession;

import com.isn.quizplatform.model.Proposition;
import com.isn.quizplatform.service.PropositionService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class PropositionController {
	
	
	
	@Autowired
	private PropositionService propositionService;
	
	@GetMapping("/public/proposition/{id}")
	public ApiResponse<Proposition> getPropositionById(@PathVariable Long id) {
		return propositionService.getPropositionById(id);
	}
	
	
	
	@PutMapping("/admin/propositions/{id}")
	public ApiResponse<Proposition> updateProposition(@PathVariable Long id, @RequestBody Proposition propositionInfo) {
		return propositionService.updateProposition(id, propositionInfo);
	}
	
	@DeleteMapping("/admin/propositions/{id}")
	public ApiResponse<Proposition> deleteProposition(@PathVariable Long id){
		return propositionService.deleteProposition(id);
		
	}
	
}
