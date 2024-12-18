package com.isn.quizplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassementService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getClassementByQuizId(Long quizID) {
        // Appel de la procédure stockée
        String sql = "CALL CalculeClassementParQuiz(?)";
        return jdbcTemplate.queryForList(sql, quizID);
    }
    
    public List<Map<String, Object>> getPropositionsPercentage(Long questionID, Long quizID) {
        // Appel de la procédure stockée
        System.out.println("Calling GetPropositionsPercentage with questionID: " + questionID + " and quizID: " + quizID);
        String sql = "CALL GetPropositionsPercentage(?, ?)";
        return jdbcTemplate.queryForList(sql, questionID, quizID);
    }
}