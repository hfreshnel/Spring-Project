package com.isn.quizplatform.repository;

import com.isn.quizplatform.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
