package com.isn.quizplatform.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.isn.quizplatform.model.Choisir;
import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.model.Question;
import com.isn.quizplatform.model.Quiz;

import jakarta.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test") // Utilise application-test.properties
public class ChoisirRepositoryTest {

    @Autowired
    private ChoisirRepository choisirRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    private Personne defaultPersonne1;
    private Personne defaultPersonne2;

    private Quiz defaultQuiz1;
    private Quiz defaultQuiz2;


    private List<Question> defaultQuestions;

    private Choisir defaultChoisir1;
    private Choisir defaultChoisir2;

    @BeforeEach
    public void setup() {
        choisirRepository.deleteAll(); // Nettoie la table avant chaque test

        defaultQuiz1 = new Quiz("Quiz 1", 0, new Timestamp(System.currentTimeMillis()), 0, 1, new Timestamp(System.currentTimeMillis()), new ArrayList<>());
        defaultQuiz2 = new Quiz("Quiz 2", 0, new Timestamp(System.currentTimeMillis()), 0, 1, new Timestamp(System.currentTimeMillis()), new ArrayList<>());

        defaultQuiz1 = quizRepository.save(defaultQuiz1);
        defaultQuiz2 = quizRepository.save(defaultQuiz2);

        Question question1 = new Question("Question 1");
        Question question2 = new Question("Question 2");
        Question question3 = new Question("Question 3");
        Question question4 = new Question("Question 4");

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        questionRepository.save(question4);

        defaultQuestions = new ArrayList<>();
        defaultQuestions.add(question1);
        defaultQuestions.add(question2);
        defaultQuestions.add(question3);
        defaultQuestions.add(question4);

        defaultPersonne1 = new Personne("Dupont", "Jean", "jean.dupont@example.com", "password123", 1);
        defaultPersonne2 = new Personne("John", "Jones", "kohn.jones@example.com", "1234", 1000);

        defaultPersonne1 = personneRepository.save(defaultPersonne1);
        defaultPersonne2 = personneRepository.save(defaultPersonne2);

        defaultChoisir1 = new Choisir(defaultPersonne1, defaultQuiz1, new Timestamp(System.currentTimeMillis()));
        defaultChoisir2 = new Choisir(defaultPersonne2, defaultQuiz2, new Timestamp(System.currentTimeMillis()));

        defaultChoisir1 = choisirRepository.save(defaultChoisir1);
        defaultChoisir2 = choisirRepository.save(defaultChoisir2);

    }

    @Test
    @Transactional
    public void testSaveAndFindByMailChoisir() {
        // Given
        // When
        Choisir foundChoisir = choisirRepository.findById(defaultChoisir1.getId()).orElse(null);

        Personne personne = foundChoisir.getPersonne();
        Quiz quiz = foundChoisir.getQuiz();

        // Then
        assertThat(foundChoisir).isNotNull();
        assertThat(foundChoisir.getId()).isEqualTo(defaultChoisir1.getId());
        assertThat(personne).isEqualTo(defaultPersonne1);
        assertThat(quiz).isEqualTo(defaultQuiz1);
    }

    @Test
    @Transactional
    public void testFindByIdChoisir() {
        // Given
        // When
        Choisir foundChoisir = choisirRepository.findById(defaultChoisir1.getId()).orElse(null);

        Personne personne = foundChoisir.getPersonne();
        Quiz quiz = foundChoisir.getQuiz();

        // Then
        assertThat(foundChoisir).isNotNull();
        assertThat(foundChoisir.getId()).isEqualTo(defaultChoisir1.getId());
        assertThat(personne.getNom()).isEqualTo("Dupont");
        assertThat(personne.getPrenom()).isEqualTo("Jean");
        assertThat(quiz).isEqualTo(defaultQuiz1);
    }

    @Test
    @Transactional
    public void testDelete() {
        // Given
        // When
        choisirRepository.delete(defaultChoisir1);
        Choisir foundChoisir = choisirRepository.findById(defaultChoisir1.getId()).orElse(null);

        // Then
        assertThat(foundChoisir).isNull();
    }
}