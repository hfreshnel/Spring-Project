package com.isn.quizplatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isn.quizplatform.model.Question;
import com.isn.quizplatform.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
    private QuestionRepository questionimpl;
	
	public QuestionService() {
	}
    public List<Question> getAllQuestionsByQuizId(Long quizId) {
        return questionimpl.findAll(); 
    }

    public Optional<Question> getQuestionById(Long questionId) {
        return questionimpl.findById(questionId);
    }

    public Question createQuestion(Question question) {
        return questionimpl.save(question);
    }

    public Question updateQuestion(Question updatedQuestion) {
        return questionimpl.save(updatedQuestion);
    }

    

    public boolean deleteQuestion(Long questionId) {
        if (questionimpl.existsById(questionId)) {
        	questionimpl.deleteById(questionId);
            return true;
        }
        return false;
    }
	
	

}
