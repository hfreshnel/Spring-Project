package com.isn.quizplatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isn.quizplatform.model.ApiResponse;
import com.isn.quizplatform.model.Question;
import com.isn.quizplatform.repository.QuestionRepository;

@Service
public class QuestionService {

	
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public ApiResponse<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = questionRepository.findAll();
            return new ApiResponse<>(questions, 200, null); 
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "question.failed"); 
        }
    }

    public ApiResponse<Question> getQuestionById(Long questionId) {
        try {
            Optional<Question> question = questionRepository.findById(questionId);
            if (question.isPresent()) {
                return new ApiResponse<>(question.get(), 200, null); 
            } else {
                return new ApiResponse<>(null, 404, "question.not_found"); 
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "question.failed"); 
        }
    }

    public ApiResponse<Question> createQuestion(Question question) {
        try {
            Question savedQuestion = questionRepository.save(question);
            return new ApiResponse<>(savedQuestion, 201, null); 
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Création ratée"); 
        }
    }

    public ApiResponse<Question> updateQuestion(Question updatedQuestion) {
        try {
            if (questionRepository.existsById(updatedQuestion.getId())) {
                Question savedQuestion = questionRepository.save(updatedQuestion);
                return new ApiResponse<>(savedQuestion, 200, null); 
            } else {
                return new ApiResponse<>(null, 404, "Question non trouvé"); 
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, 500, "Modification ratée"); 
        }
    }

    public ApiResponse<Boolean> deleteQuestion(Long questionId) {
        try {
            if (questionRepository.existsById(questionId)) {
                questionRepository.deleteById(questionId);
                return new ApiResponse<>(true, 200, null); 
            } else {
                return new ApiResponse<>(false, 404, "Question non trouvé"); 
            }
        } catch (Exception e) {
            return new ApiResponse<>(false, 500, "Suppression ratée"); 
        }
    }
}
