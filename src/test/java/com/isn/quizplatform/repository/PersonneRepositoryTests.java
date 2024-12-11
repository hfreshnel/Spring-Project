package com.isn.quizplatform.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.isn.quizplatform.model.Personne;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@ActiveProfiles("test")  // Utilisation du profil 'test'
@SpringBootTest(classes = com.isn.quizplatform.application.Application.class)
public class PersonneRepositoryTests {

    @Autowired
    private PersonneRepository repository;

    @Test
    void testFindById() {
        // Arrange
        Personne mockPersonne = new Personne("Math", "Anderson", "test@mail", "test", 0);
        mockPersonne.setId(1L);
        repository.save(mockPersonne); // Sauvegarde dans la base H2 en mémoire

        // Act
        Optional<Personne> found = repository.findById(1L);

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getNom()).isEqualTo("Math");
        assertThat(found.get().getPrenom()).isEqualTo("Anderson");
        assertThat(found.get().getMail()).isEqualTo("test@mail");
    }
}

