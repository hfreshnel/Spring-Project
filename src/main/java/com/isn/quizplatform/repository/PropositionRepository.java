package com.isn.quizplatform.repository;


import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.model.Proposition;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {
	

}

