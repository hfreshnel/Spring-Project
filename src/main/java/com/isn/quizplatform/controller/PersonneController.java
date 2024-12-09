package com.isn.quizplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.service.PersonneService;

@RestController
public class PersonneController{
    
	@Autowired
	private PersonneService PS;
	
	@GetMapping("/admin/personnes")
	public List<Personne> getAllPersonne(){
		return PS.getAllPersonne();
	}
	
	@GetMapping("/public/personnes/{id}")
	public Personne getPersonneById(@PathVariable Long id) {
		return PS.getPersonneById(id);
	}
	
	@GetMapping("/public/personnes/{id}")
	public Personne updatePersonne(@PathVariable Long id, @RequestBody Personne personneInfo) {
		return PS.updatePersonne(id, personneInfo);
	}
	
	@GetMapping("/admin/personnes/{id}")
	public void deletePersonne(@PathVariable Long id) {
		PS.deletePersonne(id);
	}
	
}
