package com.isn.quizplatform.service;

import java.util.List;

import com.isn.quizplatform.model.Proposition;


public interface PropositionService {
	
	Proposition creer(Proposition proposition);
	
	List <Proposition> lire();
	
	Proposition modifier (Long id, Proposition proposition);
	
	String supprimer (Long id);
	
}
