package com.isn.quizplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.isn.quizplatform.model.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import com.isn.quizplatform.repository.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ChoisirService {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private ChoisirRepository choisirRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private PropositionRepository propositionRepository;
	
	public List<Map<String, Object>> getClassementByQuizId(Long quizID) {
        // Appel de la procédure stockée
        String sql = "CALL CalculeClassementParQuiz(?)";
        return jdbcTemplate.queryForList(sql, quizID);
    }
    
    public List<Map<String, Object>> getPropositionsPercentage(Long questionID, Long quizID) {
        // Appel de la procédure stockée
    	System.out.println("Calling GetPropositionsPercentage with questionID: " + questionID + " and quizID: " + quizID);
        String sql = "CALL GetPropositionsPercentage(?, ?)";
        return jdbcTemplate.queryForList(sql, questionID, quizID);
    }
    
    public String addProposition(Long quizId, Long propositionId, Long userId) {
        // Vérifier que les IDs ne sont pas nuls
        if (quizId == null || userId == null || propositionId == null) {
            throw new IllegalArgumentException("Tous les IDs doivent être renseignés");
        }

        // Charger les entités liées (Personne, Quiz, Proposition) via leurs repositories
        Personne personne = personneRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + userId));
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz non trouvé avec l'ID : " + quizId));
        Proposition proposition = propositionRepository.findById(propositionId)
                .orElseThrow(() -> new IllegalArgumentException("Proposition non trouvée avec l'ID : " + propositionId));

        // Créer une nouvelle instance de Choisir
        Choisir choisir = new Choisir();
        choisir.setHeure(Timestamp.valueOf(LocalDateTime.now())); // Définit l'heure actuelle
        choisir.setPersonne(personne); // Associer la personne
        choisir.setQuiz(quiz); // Associer le quiz
        choisir.setProposition(proposition); // Associer la proposition

        // Sauvegarder dans la base de données
        choisirRepository.save(choisir);

        // Retourner un message de succès ou l'heure d'ajout
        return "Proposition ajoutée avec succès à " + choisir.getHeure();
    }
    
}
