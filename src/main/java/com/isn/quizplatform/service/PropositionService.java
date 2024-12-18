package com.isn.quizplatform.service;

import java.util.List;
import java.util.Optional;

import com.isn.quizplatform.model.Proposition;
import com.isn.quizplatform.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.repository.PersonneRepository;
import com.isn.quizplatform.repository.PropositionRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class PropositionService {
	
	


		private PropositionRepository PR;
		
		
	    public PropositionService(PropositionRepository PR) {
	        this.PR = PR;
	        
	    }

		public ApiResponse<Proposition> create(Proposition proposition) {
			try {
				
				return new ApiResponse<>(proposition, 200, null); // Succès, utilisateur créé
			} catch (Exception e) {
				return new ApiResponse<>(null, 500, "creation.failed"); // Erreur interne
			}
		}
		
		public ApiResponse<Proposition> getPropositionById(Long id) {
			try{
				Optional<Proposition> proposition = PR.findById(id);
				if(proposition.isPresent()){
					return new ApiResponse<>(proposition.get(),201,null);
				}else {
					return new ApiResponse<>(null,500,"Get personne failde !");
				}
			}catch (Exception e){
				return new ApiResponse<>(null,400,"The id is invailde !");
			}
		}
		
		//Update the info insert by userid
	    public ApiResponse<Proposition> updateProposition(Long id, Proposition propositionInfo) {
			try{
	    	Proposition proposition = PR.findById(id).orElseThrow();
	    	proposition.setCorrect(propositionInfo.getCorrect());
	    	proposition.setLibelle(propositionInfo.getLibelle());

	        PR.save(proposition);
			return new ApiResponse<>(proposition,201,null);
			}catch (RuntimeException e){
				return new ApiResponse<>(null,500,"Update failed !");
			}
	    }
		
		

	}

