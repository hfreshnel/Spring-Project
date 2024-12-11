package com.isn.quizplatform.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isn.quizplatform.model.*;

 
public interface QuestionRepository extends JpaRepository<Question, Long> {
	List<Question> findAllByQuizId(Long Quizid);
}