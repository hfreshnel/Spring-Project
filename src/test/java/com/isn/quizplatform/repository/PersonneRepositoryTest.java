package com.isn.quizplatform.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.isn.quizplatform.model.Personne;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // Utilise application-test.properties
public class PersonneRepositoryTest {

    @Autowired
    private PersonneRepository personneRepository;

    private Personne defaultPersonne1;
    private Personne defaultPersonne2;
    private Personne savedPersonne;

    @BeforeEach
    public void setup() {
        personneRepository.deleteAll(); // Nettoie la table avant chaque test
        defaultPersonne1 = new Personne("Dupont", "Jean", "jean.dupont@example.com", "password123", 1);
        defaultPersonne2 = new Personne("john", "Jones", "john.jones@example.com", "1234", 1000);

        savedPersonne = personneRepository.save(defaultPersonne1);
    }


    @Test
    public void testSaveAndFindByMailPersonne() {
        // Given
        // When
        Personne foundPersonne1 = personneRepository.findByMail(defaultPersonne1.getMail()).orElse(null);
        Personne foundPersonne2 = personneRepository.findByMail(defaultPersonne2.getMail()).orElse(null);


        // Then
        assertThat(foundPersonne2).isNull();
        assertThat(foundPersonne1).isNotNull();
        assertThat(foundPersonne1.getId()).isNotNull();
        assertThat(foundPersonne1.getNom()).isEqualTo("Dupont");
        assertThat(foundPersonne1.getPrenom()).isEqualTo("Jean");
        assertThat(foundPersonne1.getMail()).isEqualTo("jean.dupont@example.com");
        assertThat(foundPersonne1.getMdp()).isEqualTo("password123");
        assertThat(foundPersonne1.getRole()).isEqualTo(1);
    }

    @Test
    public void testFindByIdPersonne() {
        // Given
        // When
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
    public void testDelete() {
        // Given
        // When
        personneRepository.delete(defaultPersonne1);
        Personne foundPersonne = personneRepository.findById(savedPersonne.getId()).orElse(null);

        // Then
        assertThat(foundPersonne).isNull();
    }
}
