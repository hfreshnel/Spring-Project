package com.isn.quizplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isn.quizplatform.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
    
}