package com.repository;

@Entity
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int correct;
    private String libelle;
    private Long id;
    


    public Proposition(){}

    public Proposition (int correct, String libelle, Long id)
    {   
        super();
        this.correct = correct;
        this.libelle = libelle;
        this.id = id;
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
