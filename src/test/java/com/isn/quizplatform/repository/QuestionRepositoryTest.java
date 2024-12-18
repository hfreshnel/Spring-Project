package com.isn.quizplatform.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.isn.quizplatform.model.Proposition;
import com.isn.quizplatform.model.Question;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test") // Utilise application-test.properties
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    private Question defaultQuestion1;
    private Question savedQuestion;

    @BeforeEach
    public void setup() {
        questionRepository.deleteAll(); // Nettoie la table avant chaque test
    
        List<Proposition> propositions = new ArrayList<>();
        propositions.add(new Proposition(1, "Proposition 1"));
        propositions.add(new Proposition(0, "Proposition 2"));
        propositions.add(new Proposition(1, "Proposition 3"));
    
        defaultQuestion1 = new Question("Oui ?", propositions);
    
        savedQuestion = questionRepository.save(defaultQuestion1);
    }
    

    @Test
    public void testFindByIdQuestion() {
        // When
        Question foundQuestion = questionRepository.findById(savedQuestion.getId()).orElse(null);

        // Then
        assertThat(foundQuestion).isNotNull();
        assertThat(foundQuestion.getId()).isNotNull();
        assertThat(foundQuestion.getLibelle()).isEqualTo("Oui ?");
        assertThat(foundQuestion.getPropositions()).hasSize(3);
    }

    @Test
    public void testDelete() {
        // When
        questionRepository.delete(defaultQuestion1);
        Question foundQuestion = questionRepository.findById(savedQuestion.getId()).orElse(null);

        // Then
        assertThat(foundQuestion).isNull();
    }
}
