package com.isn.quizplatform.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
 
import com.isn.quizplatform.model.ApiResponse;
import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.repository.PersonneRepository;
import com.isn.quizplatform.service.ChoisirService;
 
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@CrossOrigin(origins = "*")
@RestController
public class ChoisirController {
 
	@Autowired
	private ChoisirService chosirService;
	@Autowired
	private PersonneRepository personneRepository;

	@GetMapping("/public/quiz/{quiz_id}/classement")
    public ApiResponse<List<Map<String, Object>>> getClassement(@PathVariable Long quiz_id) {
        return new ApiResponse<List<Map<String, Object>>>(chosirService.getClassementByQuizId(quiz_id),200,null);
    }
	 @GetMapping("/public/quiz/{quiz_id}/questions/{question_id}/stats")
	    public ApiResponse<List<Map<String, Object>>> getPropositionsPercentage(@PathVariable Long question_id,@PathVariable Long quiz_id) {
	        System.out.println("Controller: Received request for /propositions-percentage with questionID: " + question_id + " and quizID: " + quiz_id);
	    	return new ApiResponse<List<Map<String, Object>>>(chosirService.getPropositionsPercentage(question_id,quiz_id),200,null);
	 }
	 
	 @PostMapping("/public/quiz/{quiz_id}/proposition/{proposition_id}/choix")
	    public ApiResponse<String> selectPro(@PathVariable Long quiz_id,@PathVariable Long proposition_id) {
	    Long idUser = getCurrentUserId();
        System.out.println("Controller: Received request for /propositions-percentage with questionID: " + proposition_id + " and quizID: " + quiz_id);
	    	return new ApiResponse<String>(chosirService.addProposition(quiz_id,proposition_id,idUser),200,null);

	 }
	 public Long getCurrentUserId() {
		    try {
		        // Récupérer l'utilisateur authentifié
		        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		        if (authentication == null || !authentication.isAuthenticated()) {
		            throw new IllegalStateException("Utilisateur non authentifié.");
		        }

		        // Récupérer le nom de l'utilisateur (email) à partir de l'Authentication
		        Object principal = authentication.getPrincipal();
		        String currentUser = null;
		        
		        if (principal instanceof String) {
		            currentUser = (String) principal;
		        }

		        if (currentUser == null || currentUser.isEmpty()) {
		            throw new IllegalArgumentException("Le nom de l'utilisateur est introuvable dans le contexte d'authentification.");
		        }

		        System.out.println("Voici name : " + currentUser);

		        // Vérifier si l'utilisateur existe dans la base
		        Optional<Personne> person = personneRepository.findByMail(currentUser);
		        if (person.isEmpty()) {
		            throw new NoSuchElementException("Aucun utilisateur trouvé avec cet email : " + currentUser);
		        }

		        // Extraire l'ID de l'utilisateur
		        Long idUser = person.get().getId();
		        System.out.println("Voici IdUser : " + idUser);
		        return idUser;

		    } catch (Exception e) {
		        System.err.println("Erreur lors de la récupération de l'utilisateur authentifié : " + e.getMessage());
		        throw e; // Relancer l'exception pour qu'elle soit gérée par le framework si nécessaire
		    }
		}

}
