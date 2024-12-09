package com.isn.quizplatform.service;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.isn.quizplatform.model.Proposition;

import com.isn.quizplatform.repository.PropositionRepository;


@Service
@AllArgsConstructor
public class PropositionServiceImpl implements PropositionService {
	
	private final PropositionRepository propositionRepository = null;

	@Override
	public Proposition creer(Proposition proposition) {
		
		return propositionRepository.save(proposition);
	}

	@Override
	public List<Proposition> lire() {
		return propositionRepository.findAll();
	}

	@Override
    public Proposition modifier(Long id, Proposition proposition) {
        // Met à jour une proposition existante si elle existe
        return propositionRepository.findById(id).map(p -> {
            p.setLibelle(proposition.getLibelle());
            p.setCorrect(proposition.getCorrect());
            return propositionRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Proposition non trouvée avec l'ID : " + id));
    }

	public String supprimer(Long id) {
        // Supprime une proposition si elle existe
        if (propositionRepository.existsById(id)) {
            propositionRepository.deleteById(id);
            return "Proposition supprimée avec succès.";
        } else {
            throw new RuntimeException("Proposition non trouvée avec l'ID : " + id);
        }
    }

}
