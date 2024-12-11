package com.isn.quizplatform.repository;

import com.isn.quizplatform.model.Personne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PersonneRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonneRepository repository;

    @Test
    void testFindById() {
        // Arrange : Préparation des données
        Personne personne = new Personne("Math", "Anderson", "test@mail", "test", 0);
        entityManager.persist(personne);
        entityManager.flush();

        // Act : Appel de la méthode à tester
        Personne found = repository.findById(personne.getId()).orElse(null);

        // Assert : Vérification des résultats
        assertThat(found).isNotNull();
        assertThat(found.getNom()).isEqualTo("Math");
        assertThat(found.getPrenom()).isEqualTo("Anderson");
        assertThat(found.getEmail()).isEqualTo("test@mail");
    }

}
