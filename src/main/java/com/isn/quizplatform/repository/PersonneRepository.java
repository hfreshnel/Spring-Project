package com.isn.quizplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isn.quizplatform.model.*;

public interface PersonneRepository extends JpaRepository<Personne, Long>{

	Optional<Personne> findByEmail(String email);

}

