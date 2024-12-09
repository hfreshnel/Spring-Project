package com.isn.quizplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.repository.PersonneRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

	@Autowired
	private PersonneRepository PR;
	
	public void register(Personne personne) {
		PR.findByEmail(personne.getMail())
		.ifPresent(existingPersonne -> {
			throw new RuntimeException("Un utilisateur avec cet email existe déjà");
		});
		
		PR.save(personne);
		}
	
	public void login(String email, String password, HttpSession session) {
		Personne personne = PR.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail ou mot de passe invalide"));

        if (!personne.getMdp().equals(password)) {
            throw new RuntimeException("E-mail ou mot de passe invalide");
        }
        
        session.setAttribute("userId", personne.getId());
        session.setAttribute("userEmail", personne.getMail());
        System.out.println("Utilisateur connecté avec succès : " + personne.getMail());
	}
	
	public void logout(HttpSession session) {
		session.invalidate();
	    System.out.println("Utilisateur déconnecté avec succès");
	}
	
}
