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
import java.util.stream.Collectors;

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
            return new ApiResponse<>(null, 500, "quiz.fetch_failed");
        }
    }

    // Récupérer un quiz spécifique par son ID
    public ApiResponse<Quiz> getQuizById(Long id) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(id);
            if (quiz.isPresent()) {
                return new ApiResponse<>(quiz.get(), 200, null);
            } else {
                return new ApiResponse<>(null, 404, "quiz.not_found");
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "quiz.fetch_failed");
        }
    }

    // Créer un nouveau quiz
    public ApiResponse<Quiz> createQuiz(Quiz quiz) {
        try {
            Quiz newQuiz = quizRepository.save(quiz);
            return new ApiResponse<>(newQuiz, 201, "quiz.created_successfully");
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "quiz.creation_failed");
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
                return new ApiResponse<>(updatedQuiz, 200, "quiz.update_successful");
            } else {
                return new ApiResponse<>(null, 404, "quiz.not_found");
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "quiz.update_failed");
        }
    }

    // Supprimer un quiz
    public ApiResponse<Boolean> deleteQuiz(Long id) {
        try {
            if (quizRepository.existsById(id)) {
                quizRepository.deleteById(id);
                return new ApiResponse<>(true, 200, "quiz.delete_successful");
            } else {
                return new ApiResponse<>(false, 404, "quiz.not_found");
            }
        } catch (Exception e) {
            return new ApiResponse<>(false, 500, "quiz.delete_failed");
        }
    }

    //Récupérer la liste des ids de question d'un quiz
    public ApiResponse<List<Long>> getQuestionIdsByQuizId(Long id) {
        try {
            return quizRepository.findById(id)
                .map(quiz -> {
                    List<Long> questionIds = quiz.getQuestions().stream()
                            .map(Question::getId)
                            .collect(Collectors.toList());
                    return new ApiResponse<>(questionIds, 200, "Success");
                })
                .orElseGet(() -> new ApiResponse<>(null, 404, "Quiz not found."));
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Erreur lors de la récupération des ids");
        }
    }

    // Ajouter question à un quiz
    public ApiResponse<Quiz> addQuestionToQuiz(Long quizId, Long questionId) {
        ApiResponse<Quiz> response = new ApiResponse<>();

        try {
            // Récupérer le quiz existant
            Quiz quiz = quizRepository.findById(quizId).orElse(null);
            if (quiz == null) {
                response.setError("quiz.not_found");
                response.setCode(404);
                return response;
            }

            // Récupérer la question et vérifier si elle existe
            Question question = questionRepository.findById(questionId).orElse(null);
            if (question == null) {
                response.setError("quiz.not_found");
                response.setCode(404);
                return response;
            }

            // Ajouter la question au quiz
            quiz.getQuestions().add(question);
            quizRepository.save(quiz);

            // Réponse en cas de succès
            response.setData(quiz);
            response.setError("quiz.question_added");
            response.setError(null);
            return response;

        } catch (Exception e) {
            response.setError("quiz.question_add_failed");
            response.setCode(500);
            return response;
        }
    }
}
