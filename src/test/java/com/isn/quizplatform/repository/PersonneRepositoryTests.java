package com.isn.quizplatform.repository;

import com.isn.quizplatform.model.Personne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest
public class PersonneRepositoryTests {

    @Autowired
    private PersonneRepository personneRepository;

    @Test
    public void testSavePersonne() {
        Personne personne = new Personne("Dupont", "Jean", "jean.dupont@example.com", "password123", 1);

        Personne savedPersonne = personneRepository.save(personne);

        assertThat(savedPersonne).isNotNull();
        assertThat(savedPersonne.getId()).isGreaterThan(0);
        assertThat(savedPersonne.getNom()).isEqualTo("Dupont");
        assertThat(savedPersonne.getPrenom()).isEqualTo("Jean");
    }

    // @Test
    // public void testFindByMail() {
    // // Given
    // Personne personne = new Personne("Martin", "Pierre",
    // "pierre.martin@example.com", "password123", 2);
    // personneRepository.save(personne);

    // // When
    // Personne foundPersonne =
    // personneRepository.findByMail("pierre.martin@example.com");

    // // Then
    // assertThat(foundPersonne).isNotNull();
    // assertThat(foundPersonne.getMail()).isEqualTo("pierre.martin@example.com");
    // }
}
