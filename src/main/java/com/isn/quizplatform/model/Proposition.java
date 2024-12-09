package com.isn.quizplatform.model;

public interface Proposition {
	public static final long id = 0;
	public static final String libelle = "";
	public static final int correct = 0;
	
	public long getId();
	public String getLibelle();
	public int getCorrect();
	public long setId();
	public String setLibelle(String id);
	public int setCorrect(int correct);

}
