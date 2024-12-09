package com.isn.quizplatform.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;

import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.repository.PersonneRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
			ApplicationContext context = SpringApplication.run(Application.class, args);

			// Obtention du bean PersonneRepository
			PersonneRepository personneRepository = context.getBean(PersonneRepository.class);

			// Création d'une personne
			Personne personne = new Personne("Math", "Anderson", "test@mail", "test", 0);
	
			// Sauvegarde dans la base de données
			personneRepository.save(personne);
	
			// Vérification de l'enregistrement
			System.out.println("Personne sauvegardée : " + personne);
	}

}
