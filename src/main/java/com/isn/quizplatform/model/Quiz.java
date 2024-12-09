package com.isn.quizplatform.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle; // Nom ou titre du quiz

    @Column(nullable = false)
    private int etat; // 0 : pas commencé, 10 : en cours, 20 : terminé

    private LocalDateTime dateDebutQuiz; // Date de début du quiz

    private Integer noQuestionCourante; // Numéro de la question actuelle

    private Integer etape; // 1 : afficher question, 2 : afficher stats, 3 : afficher classement

    private LocalDateTime dateDebutQuestion; // Date de début de la question actuelle

    // Relation avec l'entité `Question`
    // @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<Question> questions;

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

    public LocalDateTime getDateDebutQuiz() {
        return dateDebutQuiz;
    }

    public void setDateDebutQuiz(LocalDateTime dateDebutQuiz) {
        this.dateDebutQuiz = dateDebutQuiz;
    }

    public Integer getNoQuestionCourante() {
        return noQuestionCourante;
    }

    public void setNoQuestionCourante(Integer noQuestionCourante) {
        this.noQuestionCourante = noQuestionCourante;
    }

    public Integer getEtape() {
        return etape;
    }

    public void setEtape(Integer etape) {
        this.etape = etape;
    }

    public LocalDateTime getDateDebutQuestion() {
        return dateDebutQuestion;
    }

    public void setDateDebutQuestion(LocalDateTime dateDebutQuestion) {
        this.dateDebutQuestion = dateDebutQuestion;
    }

    // public List<Question> getQuestions() {
    //     return questions;
    // }

    // public void setQuestions(List<Question> questions) {
    //     this.questions = questions;
    // }
}
