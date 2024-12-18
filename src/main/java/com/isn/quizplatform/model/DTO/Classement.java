package com.isn.quizplatform.model.DTO;

public class Classement {
    private Long personId;
    private Long correctAnswers;
    private Long totalResponseTime;
    private Long ranking;

    public Classement(){        
    }

    public Classement(Long personId, Long correctAnswers, Long totalResponseTime, Long ranking){
        this.personId = personId;
        this.correctAnswers = correctAnswers;
        this.totalResponseTime = totalResponseTime;
        this.ranking = ranking;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Long correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Long getTotalResponseTime() {
        return totalResponseTime;
    }

    public void setTotalResponseTime(Long totalResponseTime) {
        this.totalResponseTime = totalResponseTime;
    }

    public Long getRanking() {
        return ranking;
    }

    public void setRanking(Long ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Classement{");
        sb.append("personId=").append(personId);
        sb.append(", correctAnswers=").append(correctAnswers);
        sb.append(", totalResponseTime=").append(totalResponseTime);
        sb.append(", ranking=").append(ranking);
        sb.append('}');
        return sb.toString();
    }
    

}

