package com.isn.quizplatform.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.isn.quizplatform.application.Application;
import com.isn.quizplatform.model.Personne;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class PersonneRepositoryTests {

    @Autowired
    private PersonneRepository personneRepository;

    @Test
    public void testSavePersonne() {
        // Given
        Personne personne = new Personne("Dupont", "Jean", "jean.dupont@example.com", "password123", 1);
 
        // When
        Personne savedPersonne = personneRepository.save(personne);
 
        // Then
        assertThat(savedPersonne).isNotNull();
        assertThat(savedPersonne.getId()).isGreaterThan(0);
        assertThat(savedPersonne.getNom()).isEqualTo("Dupont");
        assertThat(savedPersonne.getPrenom()).isEqualTo("Jean");
    }
}

