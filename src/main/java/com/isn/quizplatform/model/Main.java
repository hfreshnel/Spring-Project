package com.isn.quizplatform.model;

public class Main {
	 public static void main(String[] args) {
	        // Création d'une réponse sans données (data)
	        ApiResponse<String> responseWithoutData = new ApiResponse<>(404, "Objet introuvable");
	        System.out.println(responseWithoutData);

	      
	        Personne personne = new Personne(
	                "Doe", 
	                "John", 
	                "johndoe@example.com", 
	                "password123", 
	                1
	            );

	            // Utilisation de la classe Response avec Personne comme paramètre générique
	            ApiResponse<Personne> response = new ApiResponse<>(
	                personne, // Objet Personne
	                200,      // Code succès
	                "Opération réussie" // Message
	            );

	            // Affichage du résultat
	            System.out.println(response);
	    }
}
