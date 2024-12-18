package com.isn.quizplatform.model.DTO;

public class Statistique {
    private Long QuizID;
    private Long QuestionID;
    private Long PropositionID;
    private Long ChoicesForProposition;
    private Long Percentage;

    public Statistique() {
    }

    public Statistique(Long ChoicesForProposition, Long Percentage, Long PropositionID, Long QuestionID, Long QuizID) {
        this.ChoicesForProposition = ChoicesForProposition;
        this.Percentage = Percentage;
        this.PropositionID = PropositionID;
        this.QuestionID = QuestionID;
        this.QuizID = QuizID;
    }

    public Long getQuizID() {
        return QuizID;
    }

    public void setQuizID(Long quizID) {
        QuizID = quizID;
    }

    public Long getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(Long questionID) {
        QuestionID = questionID;
    }

    public Long getPropositionID() {
        return PropositionID;
    }

    public void setPropositionID(Long propositionID) {
        PropositionID = propositionID;
    }

    public Long getChoicesForProposition() {
        return ChoicesForProposition;
    }

    public void setChoicesForProposition(Long choicesForProposition) {
        ChoicesForProposition = choicesForProposition;
    }

    public Long getPercentage() {
        return Percentage;
    }

    public void setPercentage(Long percentage) {
        Percentage = percentage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Statistique{");
        sb.append("QuizID=").append(QuizID);
        sb.append(", QuestionID=").append(QuestionID);
        sb.append(", PropositionID=").append(PropositionID);
        sb.append(", ChoicesForProposition=").append(ChoicesForProposition);
        sb.append(", Percentage=").append(Percentage);
        sb.append('}');
        return sb.toString();
    }
}

