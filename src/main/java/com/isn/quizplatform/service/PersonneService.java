package com.isn.quizplatform.service;

import java.util.List;
import java.util.Optional;

import com.isn.quizplatform.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
	public ApiResponse<List<Personne>> getAllPersonne(){
		try {
			return new ApiResponse<List<Personne>>(PR.findAll(),201,null);
		}catch (RuntimeException e){
			return new ApiResponse<>(null, 500, "Get All users failed");
		}
	}

	//Get a user by userid
	public ApiResponse<Personne> getPersonneById(Long id) {
		try{
			Optional<Personne> personne = PR.findById(id);
			if(personne.isPresent()){
				return new ApiResponse<>(personne.get(),201,null);
			}else {
				return new ApiResponse<>(null,500,"Get personne failde !");
			}
		}catch (Exception e){
			return new ApiResponse<>(null,400,"The id is invailde !");
		}
	}

	//Update the info insert by userid
    public ApiResponse<Personne> updatePersonne(Long id, Personne personneInfo) {
		try{
    	Personne personne = PR.findById(id).orElseThrow();
    	personne.setNom(personneInfo.getNom());
		personne.setPrenom(personneInfo.getPrenom());

		String hashedPassword = passwordEncoder.encode(personneInfo.getMdp());
		personne.setMdp(hashedPassword);

		personne.setMail(personneInfo.getMail());

        PR.save(personne);
		return new ApiResponse<>(personne,201,null);
		}catch (RuntimeException e){
			return new ApiResponse<>(null,500,"Update faieled !");
		}
    }

	//Delete user by userid
    public ApiResponse<Personne> deletePersonne(Long id) {
		try {
			PR.deleteById(id);
			return new ApiResponse<>(null,201,null);
		}catch (RuntimeException e){
			return new ApiResponse<>(null,500, "Delete faied !");
		}

    }
 
}
