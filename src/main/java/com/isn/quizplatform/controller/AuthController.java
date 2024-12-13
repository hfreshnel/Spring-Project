package com.isn.quizplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.isn.quizplatform.model.LoginRequest;
import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.service.AuthService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {

	@Autowired
	private AuthService AS;
	
	@PostMapping("/public/auth/register")
	public void register(@RequestBody Personne personne) {
		AS.register(personne);
	}
	
	@PostMapping("/public/auth/login")
	public Personne  login(@RequestBody LoginRequest loginrequest) {
		return AS.login(loginrequest.getMail(), loginrequest.getPwd());
	}

}
