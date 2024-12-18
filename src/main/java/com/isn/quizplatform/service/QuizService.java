package com.isn.quizplatform.service;

import com.isn.quizplatform.model.ApiResponse;
import com.isn.quizplatform.model.Quiz;
import com.isn.quizplatform.repository.QuestionRepository;
import com.isn.quizplatform.repository.QuizRepository;
import com.isn.quizplatform.model.Question;

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
    public ApiResponse<List<Quiz>> getAllQuiz() {
        try {
            List<Quiz> quizList = quizRepository.findAll();
            return new ApiResponse<>(quizList, 200, null); // Succès
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Erreur lors de la récupération des quiz.");
        }
    }

    // Récupérer un quiz spécifique par son ID
    public ApiResponse<Quiz> getQuizById(Long id) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(id);
            if (quiz.isPresent()) {
                return new ApiResponse<>(quiz.get(), 200, null);
            } else {
                return new ApiResponse<>(null, 404, "Quiz introuvable.");
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Erreur lors de la récupération du quiz.");
        }
    }

    // Créer un nouveau quiz
    public ApiResponse<Quiz> createQuiz(Quiz quiz) {
        try {
            Quiz newQuiz = quizRepository.save(quiz);
            return new ApiResponse<>(newQuiz, 201, null); // Succès
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Erreur lors de la création du quiz.");
        }
    }

    // Mettre à jour un quiz
    public ApiResponse<Quiz> updateQuiz(Long id, Quiz quizDetails) {
        try {
            Optional<Quiz> optionalQuiz = quizRepository.findById(id);
            if (optionalQuiz.isPresent()) {
                Quiz quiz = optionalQuiz.get();
                quiz.setLibelle(quizDetails.getLibelle());
                quiz.setEtat(quizDetails.getEtat());
                quiz.setDateDebutQuiz(quizDetails.getDateDebutQuiz());
                quiz.setNoQuestionCourante(quizDetails.getNoQuestionCourante());
                quiz.setEtape(quizDetails.getEtape());
                quiz.setDateDebutQuestion(quizDetails.getDateDebutQuestion());
                Quiz updatedQuiz = quizRepository.save(quiz);
                return new ApiResponse<>(updatedQuiz, 200, null); // Succès
            } else {
                return new ApiResponse<>(null, 404, "Quiz introuvable.");
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Erreur lors de la mise à jour du quiz.");
        }
    }

    // Supprimer un quiz
    public ApiResponse<Boolean> deleteQuiz(Long id) {
        try {
            if (quizRepository.existsById(id)) {
                quizRepository.deleteById(id);
                return new ApiResponse<>(true, 200, null); // Succès
            } else {
                return new ApiResponse<>(false, 404, "Quiz introuvable.");
            }
        } catch (Exception e) {
            return new ApiResponse<>(false, 500, "Erreur lors de la suppression du quiz.");
        }
    }

    // Ajouter question à un quiz
    public ApiResponse<Quiz> addQuestionToQuiz(Long quizId, Long questionId) {
        ApiResponse<Quiz> response = new ApiResponse<>();

        try {
            // Récupérer le quiz existant
            Quiz quiz = quizRepository.findById(quizId).orElse(null);
            if (quiz == null) {
                response.setError("Quiz introuvable");
                response.setCode(404);
                return response;
            }

            // Récupérer la question et vérifier si elle existe
            Question question = questionRepository.findById(questionId).orElse(null);
            if (question == null) {
                response.setError("Question introuvable");
                response.setCode(404);
                return response;
            }

            // Ajouter la question au quiz
            quiz.getQuestions().add(question);
            quizRepository.save(quiz);

            // Réponse en cas de succès
            response.setData(quiz);
            response.setCode(200);
            response.setError(null);
            return response;

        } catch (Exception e) {
            response.setError("Erreur lors de l'ajout de la question au quiz.");
            response.setCode(500);
            return response;
        }
    }

    
}
