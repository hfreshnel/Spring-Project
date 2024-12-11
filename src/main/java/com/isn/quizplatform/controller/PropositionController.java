package com.isn.quizplatform.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isn.quizplatform.model.Proposition;
import com.isn.quizplatform.service.PropositionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/proposition")
@AllArgsConstructor
public class PropositionController {
	
	private final PropositionService propositionService = null;
	
	@PostMapping("/create")
	public Proposition create(@RequestBody Proposition proposition) {
		return propositionService.creer(proposition);
		
	}
	 
	@GetMapping
	public List<Proposition> read(){
		return propositionService.lire();
	}
	
	@PutMapping("/update/{id}")
	public Proposition update(@PathVariable Long id, @RequestBody Proposition proposition) {
		return propositionService.modifier(id, proposition);
		
	}
	
	@DeleteMapping("/delete}")
	public String delete(@PathVariable Long id) {
		return propositionService.supprimer(id);
	}
	
	
}
