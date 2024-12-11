package com.isn.quizplatform.application;

import org.springframework.boot.SpringApplication;

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
			// ApplicationContext context = SpringApplication.run(Application.class, args);

			// // Obtention du bean PersonneRepository
			// @Autowired
			// PersonneRepository personneRepository;

			// // // Création d'une personne
			// // Personne personne = new Personne("Math", "Anderson", "test@mail", "test", 0);
	
			// // Sauvegarde dans la base de données
			// personneRepository.getById("1");
	
			// // Vérification de l'enregistrement
			// System.out.println("Personne sauvegardée : " + personne);
	}

}
