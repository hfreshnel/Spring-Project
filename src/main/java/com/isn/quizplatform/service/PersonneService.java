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
			return new ApiResponse<>(PR.findAll(),200,null);
		}catch (RuntimeException e){
			return new ApiResponse<>(null, 500, "person.get_failed");
		}
	}

	//Get a user by userid
	public ApiResponse<Personne> getPersonneById(Long id) {
		try{
			Optional<Personne> personne = PR.findById(id);
			if(personne.isPresent()){
				return new ApiResponse<>(personne.get(),200,null);
			}else {
				return new ApiResponse<>(null,404,"person.get_id_failed");
			}
		}catch (Exception e){
			return new ApiResponse<>(null,500,"person.get_failed");
		}
	}

	//Update the info insert by userid
    public ApiResponse<Personne> updatePersonne(Long id, Personne personneInfo) {
		try{
    	Personne personne = PR.findById(id).orElseThrow(() -> new RuntimeException("person.get_id_failed"));
    	personne.setNom(personneInfo.getNom());
		personne.setPrenom(personneInfo.getPrenom());

		if(personneInfo.getMdp().length() < 6){
            throw new RuntimeException("person.mdp_form_wrong");
		}else {
			String hashedPassword = passwordEncoder.encode(personneInfo.getMdp());
			personne.setMdp(hashedPassword);
		}

		String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
		if(!personneInfo.getMail().matches(emailRegex)){
			return new ApiResponse<>(null, 404, "person.email_from_wrong");
		}else {
			personne.setMail(personneInfo.getMail());
		}

        PR.save(personne);
		return new ApiResponse<>(personne,200,null);
		}catch (RuntimeException e){
			return new ApiResponse<>(null,404,e.getMessage());
		}
    }

	//Delete user by userid
    public ApiResponse<Personne> deletePersonne(Long id) {
		try {
			PR.findById(id).orElseThrow(() -> new RuntimeException("person.get_id_failed"));
			PR.deleteById(id);
			return new ApiResponse<>(null,200,null);
		}catch (RuntimeException e){
			return new ApiResponse<>(null,404, e.getMessage());
		}

    }
 
}
