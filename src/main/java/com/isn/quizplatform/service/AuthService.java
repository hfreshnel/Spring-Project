package com.isn.quizplatform.service;

import com.isn.quizplatform.model.ApiResponse;
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
	
	public ApiResponse<Personne> register(Personne personne) {
			if(PR.findByMail(personne.getMail()).isPresent()){
				return new ApiResponse<>(null, 400, "The user already exists");
			}
			String hashedPassword = passwordEncoder.encode(personne.getMdp());
			personne.setMdp(hashedPassword);

			personne.setRole(0);
		try {
			PR.save(personne);
			return new ApiResponse<>(personne, 201, null); // Succès, utilisateur créé
		} catch (Exception e) {
			return new ApiResponse<>(null, 500, "registration failed"); // Erreur interne
		}
	}
	
	public ApiResponse<Personne> login(String email, String password) {
		try {
			Personne personne = PR.findByMail(email)
					.orElseThrow(() -> new RuntimeException("E-mail ou mot de passe invalide"));

			if (!passwordEncoder.matches(password, personne.getMdp())) {
				throw new RuntimeException("E-mail ou mot de passe invalide");
			}

			System.out.println("Utilisateur connecté avec succès : " + personne.getMail());
			return new ApiResponse<>(personne, 201, null);
		} catch (RuntimeException e){
			return new ApiResponse<>(null, 400, "E-mail ou mot de passe invalide");
		} catch (Exception e){
			return new ApiResponse<>(null, 500, "Login.failed");
		}
	}

}
