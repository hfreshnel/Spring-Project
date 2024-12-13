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
		try {
			PR.findByMail(personne.getMail()).ifPresent(existingPersonne -> {
				throw new RuntimeException("Un utilisateur avec cet email existe déjà");
			});

			String hashedPassword = passwordEncoder.encode(personne.getMdp());
			personne.setMdp(hashedPassword);

			PR.save(personne);
			return new ApiResponse<>(personne, 201, null); // Succès, utilisateur créé
		} catch (RuntimeException e) {
			return new ApiResponse<>(null, 400, "user.already_exists"); // Email déjà utilisé
		} catch (Exception e) {
			return new ApiResponse<>(null, 500, "registration.failed"); // Erreur interne
		}
	}
	
	public Personne login(String email, String password) {
		Personne personne = PR.findByMail(email)
                .orElseThrow(() -> new RuntimeException("E-mail ou mot de passe invalide"));

        if (!passwordEncoder.matches(password, personne.getMdp())) {
            throw new RuntimeException("E-mail ou mot de passe invalide");
        }

		if (personne.getRole() == 0) {
			personne.setRole(0);
		}

        System.out.println("Utilisateur connecté avec succès : " + personne.getMail());
		return personne;
	}

}
