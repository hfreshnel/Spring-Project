package com.isn.quizplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.repository.PersonneRepository;

@Service
public class PersonneService {
    
	@Autowired
	private PersonneRepository PR;
	
	public List<Personne> getAllPersonne(){
		return PR.findAll();
	}
	
	public Personne getPersonneById(Long id) {
		return PR.findById(id).orElse(new Personne());
	}
	
    public Personne updatePersonne(Long id, Personne personneInfo) {
    	Personne personne = getPersonneById(id);
    	personne.setNom(personneInfo.getNom());
    	personne.setMail(personneInfo.getMail());
        return PR.save(personne);
    }

    public void deletePersonne(Long id) {
    	PR.deleteById(id);
    }
 
}
