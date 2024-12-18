package com.isn.quizplatform.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity 
@Table(name = "quiz") 
public class Quiz { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generation automatique de l'id
    private Long id;

    @Column(name = "libelle", nullable = false) 
    private String libelle;

    @Column(name = "etat", nullable = false)
    private int etat;

    @Column(name = "dateDebutQuiz") 
    private Timestamp dateDebutQuiz;

    @Column(name = "numeroQuestionCourante")
    private int noQuestionCourante;

    @Column(name = "etape")
    private int etape;

    @Column(name = "dateDebutQuestion")
    private Timestamp dateDebutQuestion;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "quiz_question",
        joinColumns = @JoinColumn(name = "quiz_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

    // Constructeurs
    public Quiz() {}

    public Quiz(String libelle, int etat, Timestamp dateDebutQuiz, 
                int noQuestionCourante, int etape, Timestamp dateDebutQuestion, 
                List<Question> questions) {
        this.libelle = libelle;
        this.etat = etat;
        this.dateDebutQuiz = dateDebutQuiz;
        this.noQuestionCourante = noQuestionCourante;
        this.etape = etape;
        this.dateDebutQuestion = dateDebutQuestion;
        this.questions = questions;
    }

    public Quiz(Long id, String libelle, int etat, Timestamp dateDebutQuiz, 
                int noQuestionCourante, int etape, Timestamp dateDebutQuestion, 
                List<Question> questions) {
        this.id = id;
        this.libelle = libelle;
        this.etat = etat;
        this.dateDebutQuiz = dateDebutQuiz;
        this.noQuestionCourante = noQuestionCourante;
        this.etape = etape;
        this.dateDebutQuestion = dateDebutQuestion;
        this.questions = questions;
    }

    public Quiz(String libelle, int etat, Timestamp dateDebutQuiz, 
                int noQuestionCourante, int etape, Timestamp dateDebutQuestion) {
        this.libelle = libelle;
        this.etat = etat;
        this.dateDebutQuiz = dateDebutQuiz;
        this.noQuestionCourante = noQuestionCourante;
        this.etape = etape;
        this.dateDebutQuestion = dateDebutQuestion;
    }

    // Getters et Setters
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Timestamp getDateDebutQuiz() {
        return dateDebutQuiz;
    }

    public void setDateDebutQuiz(Timestamp dateDebutQuiz) {
        this.dateDebutQuiz = dateDebutQuiz;
    }

    public int getNoQuestionCourante() {
        return noQuestionCourante;
    }

    public void setNoQuestionCourante(int noQuestionCourante) {
        this.noQuestionCourante = noQuestionCourante;
    }

    public int getEtape() {
        return etape;
    }

    public void setEtape(int etape) {
        this.etape = etape;
    }

    public Timestamp getDateDebutQuestion() {
        return dateDebutQuestion;
    }

    public void setDateDebutQuestion(Timestamp dateDebutQuestion) {
        this.dateDebutQuestion = dateDebutQuestion;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    @Override
public String toString() {
    return "Quiz{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", etat=" + etat +
            ", dateDebutQuiz=" + dateDebutQuiz +
            ", noQuestionCourante=" + noQuestionCourante +
            ", etape=" + etape +
            ", dateDebutQuestion=" + dateDebutQuestion +
            ", questions=" + (questions != null ? questions.size() : 0) +
            '}';
}

}
