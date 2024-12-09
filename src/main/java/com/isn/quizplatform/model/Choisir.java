package com.isn.quizplatform.model;

import java.time.LocalDateTime;

public class Choisir {

	private LocalDateTime heure;

	public Choisir(LocalDateTime heure) {
		super();
		this.heure = heure;
	}

	public LocalDateTime getHeure() {
		return heure;
	}

	public void setHeure(LocalDateTime heure) {
		this.heure = heure;
	}
	
	
	
}
