package com.isn.quizplatform.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.isn.quizplatform.model.Quiz;



import com.isn.quizplatform.model.Question;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@ActiveProfiles("test")
public class QuizRepositoryTests {

    @Autowired
    private QuizRepository quizRepository;

    private Quiz defaultQuiz1;
    private Quiz defaultQuiz2;
    private Quiz savedQuiz;

    @BeforeEach
    public void setup() {
        quizRepository.deleteAll();
        Question question1 = new Question("Quelle est la capitale de la France ?");
        Question question2 = new Question("Combien de continents y a-t-il ?");
        List<Question> questions = Arrays.asList(question1, question2);
        defaultQuiz1 = new Quiz("Quiz Dupont",1, Timestamp.valueOf("2024-01-01 10:00:00"),
        3, 1,Timestamp.valueOf("2024-01-01 10:05:00"),  questions);

        defaultQuiz2 = new Quiz("Quiz Jones", 2, Timestamp.valueOf("2024-02-01 15:00:00"),
                                     5, 2, Timestamp.valueOf("2024-02-01 15:10:00"), questions);
        savedQuiz = quizRepository.save(defaultQuiz1);
    }

    
    @Transactional
    @Test
    public void testSaveAndFindByLibelleQuiz() {

        Quiz foundQuiz1 = quizRepository.findByLibelle(defaultQuiz1.getLibelle()).orElse(null);
        Quiz foundQuiz2 = quizRepository.findByLibelle(defaultQuiz2.getLibelle()).orElse(null);


        assertThat(foundQuiz2).isNull();
        assertThat(foundQuiz1).isNotNull();
        assertThat(foundQuiz1.getId()).isNotNull();
        assertThat(foundQuiz1.getLibelle()).isEqualTo("Quiz Dupont");
        assertThat(foundQuiz1.getEtape()).isEqualTo(1);
        assertThat(foundQuiz1.getDateDebutQuiz().toInstant())
        .isEqualTo(Timestamp.valueOf("2024-01-01 10:00:00").toInstant());
        assertThat(foundQuiz1.getNoQuestionCourante()).isEqualTo(3);
        assertThat(foundQuiz1.getEtape()).isEqualTo(1);
        assertThat(foundQuiz1.getDateDebutQuestion().toInstant())
        .isEqualTo(Timestamp.valueOf("2024-01-01 10:05:00").toInstant());
    assertThat(foundQuiz1.getQuestions()).isNotNull();
    assertThat(foundQuiz1.getQuestions().size()).isEqualTo(2);

    assertThat(foundQuiz1.getQuestions().get(0).getLibelle())
        .isEqualTo("Quelle est la capitale de la France ?");
    assertThat(foundQuiz1.getQuestions().get(1).getLibelle())
        .isEqualTo("Combien de continents y a-t-il ?");

    }
    
    @Transactional
    @Test
    public void testFindByIdQuiz() {

        Quiz foundQuiz = quizRepository.findById(savedQuiz.getId()).orElse(null);

        assertThat(foundQuiz).isNotNull();
        assertThat(foundQuiz.getId()).isNotNull();
        assertThat(foundQuiz.getLibelle()).isEqualTo("Quiz Dupont");
        assertThat(foundQuiz.getEtape()).isEqualTo(1);
        assertThat(foundQuiz.getDateDebutQuiz().toInstant())
        .isEqualTo(Timestamp.valueOf("2024-01-01 10:00:00").toInstant());
        assertThat(foundQuiz.getNoQuestionCourante()).isEqualTo(3);
        assertThat(foundQuiz.getEtape()).isEqualTo(1);
        assertThat(foundQuiz.getDateDebutQuestion().toInstant())
        .isEqualTo(Timestamp.valueOf("2024-01-01 10:05:00").toInstant());

    assertThat(foundQuiz.getQuestions()).isNotNull();
    assertThat(foundQuiz.getQuestions().size()).isEqualTo(2);

    assertThat(foundQuiz.getQuestions().get(0).getLibelle())
        .isEqualTo("Quelle est la capitale de la France ?");
    assertThat(foundQuiz.getQuestions().get(1).getLibelle())
        .isEqualTo("Combien de continents y a-t-il ?");
    }

@Transactional
@Test
public void testSaveQuizWithoutQuestions() {
    Quiz quizWithoutQuestions = new Quiz("Quiz Sans Questions", 1, Timestamp.valueOf("2024-01-01 10:00:00"),
                                         1, 1, Timestamp.valueOf("2024-01-01 10:05:00"), Collections.emptyList());
    

    Quiz savedQuizWithoutQuestions = quizRepository.save(quizWithoutQuestions);

    Quiz foundQuiz = quizRepository.findById(savedQuizWithoutQuestions.getId()).orElse(null);

    assertThat(foundQuiz).isNotNull(); 
    assertThat(foundQuiz.getQuestions()).isEmpty();  
}



@Transactional
@Test
public void testUpdateQuiz() {

    Quiz existingQuiz = quizRepository.findById(savedQuiz.getId()).orElse(null);

    existingQuiz.setLibelle("Quiz Mis à Jour");
    existingQuiz.setEtat(2); 
    existingQuiz.setNoQuestionCourante(4);  


    Question nouvelleQuestion = new Question("Quel est le plus grand océan du monde ?");
    Question autreQuestion = new Question("Quelle est la capitale du Japon ?");

    List<Question> questionsActuelles = new ArrayList<>(existingQuiz.getQuestions());


    questionsActuelles.add(nouvelleQuestion);
    questionsActuelles.add(autreQuestion);


    existingQuiz.setQuestions(questionsActuelles);

    quizRepository.save(existingQuiz);

    Quiz updatedQuiz = quizRepository.findById(existingQuiz.getId()).orElse(null);


    assertThat(updatedQuiz).isNotNull();
    assertThat(updatedQuiz.getLibelle()).isEqualTo("Quiz Mis à Jour");
    assertThat(updatedQuiz.getEtat()).isEqualTo(2);
    assertThat(updatedQuiz.getNoQuestionCourante()).isEqualTo(4);
    assertThat(updatedQuiz.getQuestions()).isNotNull();
    assertThat(updatedQuiz.getQuestions().size()).isEqualTo(4); // Il y a maintenant 4 questions
    assertThat(updatedQuiz.getQuestions().get(2).getLibelle()).isEqualTo("Quel est le plus grand océan du monde ?");
    assertThat(updatedQuiz.getQuestions().get(3).getLibelle()).isEqualTo("Quelle est la capitale du Japon ?");
}

@Transactional
@Test
public void testRemoveQuestionsFromQuiz() {

    Quiz existingQuiz = quizRepository.findById(savedQuiz.getId()).orElse(null);


    existingQuiz.setLibelle("Quiz Mis à Jour");

    List<Question> questionsActuelles = new ArrayList<>(existingQuiz.getQuestions());

    Question questionToRemove = questionsActuelles.get(0);
    questionsActuelles.remove(questionToRemove); 

    existingQuiz.setQuestions(questionsActuelles);
    quizRepository.save(existingQuiz);

    Quiz updatedQuiz = quizRepository.findById(existingQuiz.getId()).orElse(null);

    assertThat(updatedQuiz).isNotNull();
    assertThat(updatedQuiz.getLibelle()).isEqualTo("Quiz Mis à Jour");
    assertThat(updatedQuiz.getQuestions()).isNotNull();
    assertThat(updatedQuiz.getQuestions().size()).isEqualTo(1); 

    assertThat(updatedQuiz.getQuestions().get(0).getLibelle()).isEqualTo("Combien de continents y a-t-il ?");
}
@Transactional
@Test
public void testQuizWithNullLibelle() {
    Quiz quizWithNullLibelle = new Quiz(null, 1, Timestamp.valueOf("2024-01-01 10:00:00"),
                                        1, 1, Timestamp.valueOf("2024-01-01 10:05:00"),
                                        Collections.emptyList());

    try {
        quizRepository.save(quizWithNullLibelle);
        fail("Une exception aurait dû être levée pour un libellé null.");
    } catch (Exception e) {
        assertThat(e.getMessage()).contains("libelle");
    }
}


@Transactional
@Test
public void testQuizWithNullDateDebutQuiz() {
    Quiz quizWithNullDateDebutQuiz = new Quiz("Quiz Test", 1, null,
                                              1, 1, Timestamp.valueOf("2024-01-01 10:05:00"),
                                              Collections.emptyList());

    try {
        quizRepository.save(quizWithNullDateDebutQuiz);
        fail("Une exception aurait dû être levée pour une date de début de quiz null.");
    } catch (Exception e) {
        assertThat(e.getMessage()).contains("dateDebutQuiz");
    }
}


@Transactional
@Test
public void testQuizWithNullDateDebutQuestion() {
    Quiz quizWithNullDateDebutQuestion = new Quiz("Quiz Test", 1, Timestamp.valueOf("2024-01-01 10:00:00"),
                                                  1, 1, null, Collections.emptyList());

    try {
        quizRepository.save(quizWithNullDateDebutQuestion);
        fail("Une exception aurait dû être levée pour une date de début de question null.");
    } catch (Exception e) {
        assertThat(e.getMessage()).contains("dateDebutQuestion");
    }
}






    @Test
    public void testDelete() {
        quizRepository.delete(defaultQuiz1);
        Quiz foundQuiz = quizRepository.findById(savedQuiz.getId()).orElse(null);

        assertThat(foundQuiz).isNull();
    }
}

