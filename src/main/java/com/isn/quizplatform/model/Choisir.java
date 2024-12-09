package com.isn.quizplatform.model;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Choisir {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonne")
    private Personne personne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuiz")
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProposition")
    private Proposition proposition;

    @Column(name = "heure")
    private Timestamp heure;

    public Choisir() {
    }

    public Choisir(Long id, Personne personne, Quiz quiz, Timestamp heure) {
        this.id = id;
        this.personne = personne;
        this.quiz = quiz;
        this.heure = heure;
    }

    public Choisir(Personne personne, Quiz quiz, Timestamp heure) {
        this.personne = personne;
        this.quiz = quiz;
        this.heure = heure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Timestamp getHeure() {
        return heure;
    }

    public void setHeure(Timestamp heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Choisir [id=" + id + ", personne=" + personne + ", quiz=" + quiz + ", heure=" + heure + "]";
    }

}
