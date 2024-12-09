package com.isn.quizplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isn.quizplatform.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
}