package com.isn.quizplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isn.quizplatform.model.Choisir;

public interface ChoisirRepository extends JpaRepository<Choisir, Long> {
    
}