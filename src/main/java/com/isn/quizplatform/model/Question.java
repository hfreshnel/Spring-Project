package com.isn.quizplatform.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Classe question
 */
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name = "question_id")
    private List<Proposition> propositions;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;


    public Question() {
    }

    public Question(String libelle) {
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions;
    }

    private String toStringPropositions() {
        StringBuilder list = new StringBuilder();
        for (Proposition proposition : propositions) {
            list.append(proposition.toString()).append(", ");
        }
        // Retire la dernière virgule et l'espace, si nécessaire
        if (!propositions.isEmpty()) {
            list.setLength(list.length() - 2);
        }
        return list.toString();
    }

    @Override
    public String toString() {
        return "Question {" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", propositions={"
                + (propositions != null && !propositions.isEmpty() ? toStringPropositions() : "aucune proposition") +
                "}}";
    }

}
