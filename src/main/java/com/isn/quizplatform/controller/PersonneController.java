package com.isn.quizplatform.controller;

import java.util.List;

import com.isn.quizplatform.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.service.PersonneService;

@RestController
public class PersonneController{
    
	@Autowired
	private PersonneService PS;
	
	@GetMapping("/admin/personnes")
	public ApiResponse<List<Personne>> getAllPersonne(){
		return PS.getAllPersonne();
	}
	
	@GetMapping("/public/personnes/{id}")
	public ApiResponse<Personne> getPersonneById(@PathVariable Long id) {
		return PS.getPersonneById(id);
	}
	
	@PutMapping("/public/personnes/{id}")
	public ApiResponse<Personne> updatePersonne(@PathVariable Long id, @RequestBody Personne personneInfo) {
		return PS.updatePersonne(id, personneInfo);
	}
	
	@DeleteMapping("/admin/personnes/{id}")
	public ApiResponse<Personne> deletePersonne(@PathVariable Long id) {
		return PS.deletePersonne(id);
	}
	
}
