package com.isn.quizplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
    Optional<Quiz> findByLibelle(String libelle);
}