package com.isn.quizplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.repository.PersonneRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

	private PersonneRepository PR;
	private BCryptPasswordEncoder passwordEncoder;
	
    public AuthService(PersonneRepository PR, BCryptPasswordEncoder passwordEncoder) {
        this.PR = PR;
        this.passwordEncoder = passwordEncoder;
    }
	
	public void register(Personne personne) {
		PR.findByEmail(personne.getMail())
		.ifPresent(existingPersonne -> {
			throw new RuntimeException("Un utilisateur avec cet email existe déjà");
		});
		
		String hashedPassword = passwordEncoder.encode(personne.getMdp());
        personne.setMdp(hashedPassword);

		PR.save(personne);
		}
	
	public void login(String email, String password, HttpSession session) {
		Personne personne = PR.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail ou mot de passe invalide"));

        if (!passwordEncoder.matches(password, personne.getMdp())) {
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
