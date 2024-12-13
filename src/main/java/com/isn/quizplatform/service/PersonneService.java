package com.isn.quizplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.repository.PersonneRepository;

@Service
public class PersonneService {
    
	@Autowired
	private PersonneRepository PR;
	private BCryptPasswordEncoder passwordEncoder;

	public PersonneService(PersonneRepository PR, BCryptPasswordEncoder passwordEncoder) {
		this.PR = PR;
		this.passwordEncoder = passwordEncoder;
	}

	//Get all users
	public List<Personne> getAllPersonne(){
		return PR.findAll();
	}

	//Get a user by userid
	public Personne getPersonneById(Long id) {
		return PR.findById(id).orElse(new Personne());
	}

	//Update the info insert by userid
    public Personne updatePersonne(Long id, Personne personneInfo) {
    	Personne personne = getPersonneById(id);
    	personne.setNom(personneInfo.getNom());
    	personne.setMail(personneInfo.getMail());
		personne.setPrenom(personneInfo.getPrenom());

		String hashedPassword = passwordEncoder.encode(personne.getMdp());
		personne.setMdp(hashedPassword);

        return PR.save(personne);
    }

	//Delete user by userid
    public void deletePersonne(Long id) {
    	PR.deleteById(id);
    }
 
}
