package com.isn.quizplatform.service;



import com.isn.quizplatform.model.Quiz;
import com.isn.quizplatform.model.Question;
import com.isn.quizplatform.repository.QuizRepository;
import com.isn.quizplatform.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // Récupérer tous les quiz
    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }

    // Récupérer un quiz spécifique par son ID
    public Quiz getQuizById(Long id) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(id);
            return quiz.orElse(null);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du quiz avec ID : " + id);
            e.printStackTrace();
            // Retourner null ou lever une exception personnalisée si nécessaire
            return null;
        }
    }


    // Créer un nouveau quiz
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    
    

    // Mettre à jour un quiz
    public Quiz updateQuiz(Long id, Quiz quizDetails) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            quiz.setLibelle(quizDetails.getLibelle());
            quiz.setEtat(quizDetails.getEtat());
            quiz.setDateDebutQuiz(quizDetails.getDateDebutQuiz());
            quiz.setNoQuestionCourante(quizDetails.getNoQuestionCourante());
            quiz.setEtape(quizDetails.getEtape());
            quiz.setDateDebutQuestion(quizDetails.getDateDebutQuestion());
            return quizRepository.save(quiz);
        }
        return null;
    }

    // Supprimer un quiz
    public boolean deleteQuiz(Long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return true;
        }
        return false;
    }

  
}








