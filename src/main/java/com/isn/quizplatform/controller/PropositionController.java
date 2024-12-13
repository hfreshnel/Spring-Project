package com.isn.quizplatform.controller;

import java.util.List;


import com.isn.quizplatform.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.isn.quizplatform.model.LoginRequest;
import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.service.AuthService;

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
	
	@PostMapping("/public/proposition/create")
	public ApiResponse<Proposition> create(@RequestBody Proposition proposition) {
		return propositionService.create(proposition);
		
	}
	
	/*
	@GetMapping
	public List<Proposition> read(){
		return propositionService.lire();
	}
	
	@PutMapping("/update/{id}")
	public Proposition update(@PathVariable Long id, @RequestBody Proposition proposition) {
		return propositionService.modifier(id, proposition);
		
	}
	
	@DeleteMapping("/delete")
	public String delete(@PathVariable Long id) {
		return propositionService.supprimer(id);
	}
	*/
	
}
