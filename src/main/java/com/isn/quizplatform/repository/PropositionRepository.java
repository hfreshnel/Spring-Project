package com.isn.quizplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isn.quizplatform.model.Proposition;


public interface PropositionRepository extends JpaRepository<Proposition, Long> {
	

}

