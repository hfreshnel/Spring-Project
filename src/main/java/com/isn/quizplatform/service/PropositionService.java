package com.isn.quizplatform.service;

import java.util.List;

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
		
		

	}

