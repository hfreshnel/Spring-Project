package com.isn.repository;

import com.isn.quizplatform.Application;
import com.isn.quizplatform.model.Proposition;
import com.isn.quizplatform.repository.PropositionRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class PropositionRepositoryTest {

    @Autowired
    private PropositionRepository repository;

    @Test
    void testSaveProposition() {
        // Création d'une entité Proposition
        Proposition proposition = new Proposition();
        proposition.setCorrect(1);
        proposition.setLibelle("Test Proposition");

        // Sauvegarde dans le repository
        Proposition savedProposition = repository.save(proposition);

        // Vérifications
        assertNotNull(savedProposition.getId());
        assertEquals(1, savedProposition.getCorrect());
        assertEquals("Test Proposition", savedProposition.getLibelle());
    }

    @Test
    void testFindById() {
        // Création et sauvegarde d'une entité
        Proposition proposition = new Proposition();
        proposition.setCorrect(0);
        proposition.setLibelle("Another Test Proposition");
        Proposition savedProposition = repository.save(proposition);

        // Recherche par ID
        Optional<Proposition> retrievedProposition = repository.findById(savedProposition.getId());

        // Vérifications
        assertTrue(retrievedProposition.isPresent());
        assertEquals("Another Test Proposition", retrievedProposition.get().getLibelle());
    }

    @Test
    void testDeleteProposition() {
        // Création et sauvegarde d'une entité
        Proposition proposition = new Proposition();
        proposition.setCorrect(1);
        proposition.setLibelle("Proposition to Delete");
        Proposition savedProposition = repository.save(proposition);

        // Suppression
        repository.delete(savedProposition);

        // Vérification de la suppression
        Optional<Proposition> retrievedProposition = repository.findById(savedProposition.getId());
        assertFalse(retrievedProposition.isPresent());
    }

    @Test
    void testFindAll() {
        // Création et sauvegarde de plusieurs entités
        Proposition proposition1 = new Proposition(1, "Proposition 1", null);
        Proposition proposition2 = new Proposition(0, "Proposition 2", null);

        repository.save(proposition1);
        repository.save(proposition2);

        // Vérification
        assertEquals(2, repository.findAll().size());
    }
}