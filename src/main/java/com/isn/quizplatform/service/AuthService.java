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

	//Inscription un utilisateur
	public ApiResponse<Personne> register(Personne personne) {
		String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
			if(!personne.getMail().matches(emailRegex)){ //check from of email
				return new ApiResponse<>(null, 404, "auth.email_from_wrong");
			}
			if(PR.findByMail(personne.getMail()).isPresent()){ // check the email is? exits
				return new ApiResponse<>(null, 404, "auth.user_already_exists");
			}
			if(personne.getMdp().length() < 6){ // check the length of mdp
				return new ApiResponse<>(null, 404, "auth.mdp_form_wrong");
			}else {
				String hashedPassword = passwordEncoder.encode(personne.getMdp());
				personne.setMdp(hashedPassword);
			}
			personne.setRole(0);
		try {
			PR.save(personne);
			return new ApiResponse<>(personne, 200, null); // Succès, utilisateur créé
		} catch (Exception e) {
			return new ApiResponse<>(null, 500, "auth.registration_failed"); // Erreur interne
		}
	}

	//User login
	public ApiResponse<Personne> login(String email, String password) {
		try {
			Personne personne = PR.findByMail(email)
					.orElseThrow(() -> new RuntimeException("auth.invalid_credentials"));

			if (!passwordEncoder.matches(password, personne.getMdp())) {
				throw new RuntimeException("E-mail ou mot de passe invalid");
			}

			System.out.println("Utilisateur connecté avec succès : " + personne.getMail());
			return new ApiResponse<>(personne, 200, null);
		} catch (RuntimeException e){
			return new ApiResponse<>(null, 404, e.getMessage());
		} catch (Exception e){
			return new ApiResponse<>(null, 500, "auth.login_failed");
		}
	}

}
