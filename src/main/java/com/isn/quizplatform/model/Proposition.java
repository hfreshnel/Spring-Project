package com.isn.quizplatform.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correct")
    private int correct;

    @Column(name = "libelle")
    private String libelle;


    public Proposition(){}

    public Proposition (int correct, String libelle, Long id)
    {   
        super();
        this.correct = correct;
        this.libelle = libelle;
        this.id = id;
    }

    public Proposition (int correct, String libelle)
    {   
        this.correct = correct;
        this.libelle = libelle;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    @Override
    public String toString() {
        return "Proposition [correct=" + correct + ", libelle=" + libelle + ", id=" + id  + "]";
    }
    
} 