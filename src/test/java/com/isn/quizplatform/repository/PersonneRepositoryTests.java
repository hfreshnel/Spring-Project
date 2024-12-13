package com.isn.quizplatform.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.isn.quizplatform.model.Personne;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Utilise application-test.properties
public class PersonneRepositoryTests {

    @Autowired
    private PersonneRepository personneRepository;

    @Test
    public void testSaveAndFindByIdPersonne() {
        // Given
        Personne personne = new Personne("Dupont", "Jean", "jean.dupont@example.com", "password123", 1);
        
        // When
        Personne savedPersonne = personneRepository.save(personne);

        Personne foundPersonne = personneRepository.findById(savedPersonne.getId()).orElse(null);

        // Then
        assertThat(foundPersonne).isNotNull();
        assertThat(foundPersonne.getId()).isNotNull();
        assertThat(foundPersonne.getNom()).isEqualTo("Dupont");
        assertThat(foundPersonne.getPrenom()).isEqualTo("Jean");
        assertThat(foundPersonne.getMail()).isEqualTo("jean.dupont@example.com");
        assertThat(foundPersonne.getMdp()).isEqualTo("password123");
        assertThat(foundPersonne.getRole()).isEqualTo(1);
    }

    @Test
    public void testFindByMailPersonne() {
        // Given
        Personne personne = new Personne("Dupont", "Jean", "jean.dupont@example.com", "password123", 1);

        // When
        Personne savedPersonne = personneRepository.save(personne);

        Personne foundPersonne = personneRepository.findByMail(savedPersonne.getMail()).orElse(null);

        // Then
        assertThat(foundPersonne).isNotNull();
        assertThat(foundPersonne.getId()).isNotNull();
        assertThat(foundPersonne.getNom()).isEqualTo("Dupont");
        assertThat(foundPersonne.getPrenom()).isEqualTo("Jean");
        assertThat(foundPersonne.getMail()).isEqualTo("jean.dupont@example.com");
        assertThat(foundPersonne.getMdp()).isEqualTo("password123");
        assertThat(foundPersonne.getRole()).isEqualTo(1);
    }
}

