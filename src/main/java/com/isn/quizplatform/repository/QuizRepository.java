package com.isn.quizplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isn.quizplatform.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
}