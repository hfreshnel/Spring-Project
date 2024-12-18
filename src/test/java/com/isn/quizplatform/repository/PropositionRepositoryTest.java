package com.isn.quizplatform.repository;

import com.isn.quizplatform.Application;
import com.isn.quizplatform.model.Proposition;
import com.isn.quizplatform.repository.PropositionRepository;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.junit.jupiter.api.Test;

import org.springframework.test.context.ActiveProfiles;

import com.isn.quizplatform.model.Personne;

import java.net.ProxySelector;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Utilise application-test.properties
public class PropositionRepositoryTest {

    

    @Autowired
    private PropositionRepository propositionRepository;
    private Proposition defaultProposition1;
    private Proposition defaultProposition2;
    private Proposition savedProposition;

    @BeforeEach
    public void setup() {
        propositionRepository.deleteAll(); // Nettoie la table avant chaque test
        defaultProposition1 = new Proposition(0, "test", null);
        defaultProposition2 = new Proposition(1, "test2", 10L);

        savedProposition = propositionRepository.save(defaultProposition1);

    }
    @Test
    @Transactional
    public void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        int correct = 1;
        String libelle = "Example Proposition";

        // Act
        Proposition proposition = new Proposition(correct, libelle, id);

        // Assert
        assertEquals(id, proposition.getId(), "L'ID devrait être 1.");
        assertEquals(correct, proposition.getCorrect(), "La valeur correct devrait être 1.");
        assertEquals(libelle, proposition.getLibelle(), "Le libellé devrait correspondre à 'Example Proposition'.");
    }

    @Test
    @Transactional
    public void testSetters() {
        // Arrange
        Proposition proposition = new Proposition();
        
        // Act
        proposition.setId(2L);
        proposition.setCorrect(0);
        proposition.setLibelle("New Proposition");

        // Assert
        assertEquals(2L, proposition.getId(), "L'ID devrait être 2.");
        assertEquals(0, proposition.getCorrect(), "La valeur correct devrait être 0.");
        assertEquals("New Proposition", proposition.getLibelle(), "Le libellé devrait être 'New Proposition'.");
    }

    @Test
    @Transactional
    public void testToString() {
        // Arrange
        Proposition proposition = new Proposition(1, "Test Libelle", 3L);

        // Act
        String result = proposition.toString();

        // Assert
        assertEquals("Proposition [correct=1, libelle=Test Libelle, id=3]", result, 
                     "La méthode toString devrait retourner une chaîne formatée correctement.");
    }

    @Test
    @Transactional
    public void testSaveProposition() {

        // Assert
        assertEquals(savedProposition.getId(),1, "L'ID par défaut ne devrait pas être null après la sauvegarde.");
        assertEquals(0, savedProposition.getCorrect(), "La valeur correct par défaut devrait être 0.");
        assertEquals("test", savedProposition.getLibelle(), "Le libellé par défaut devrait être null.");
    }


    @Test
    @Transactional
    public void testFindPropositionByIdAndLibelle() {
        //Arrange
        Proposition foundProposition1 = propositionRepository.findById(defaultProposition1.getId()).orElse(null);
        Proposition foundProposition2 = propositionRepository.findById(defaultProposition2.getId()).orElse(null);

        //Assert
        assertThat(foundProposition2).isNull();
        assertThat(foundProposition1).isNotNull();
        assertThat(foundProposition1.getId()).isNotNull();
        assertThat(foundProposition1.getLibelle()).isEqualTo("test");


        
    }

}