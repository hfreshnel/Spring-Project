package com.isn.quizplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isn.quizplatform.model.LoginRequest;
import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.service.AuthService;

import jakarta.servlet.http.HttpSession;

@RestController
public class AuthController {

	@Autowired
	private AuthService AS;
	
	@GetMapping("/public/auth/register")
	public void register(@RequestBody Personne personne) {
		AS.register(personne);
	}
	
	@GetMapping("/public/auth/login")
	public void login(@RequestBody LoginRequest loginrequest, HttpSession session) {
		AS.login(loginrequest.getEmail(), loginrequest.getPassword(), session);
	}
	
	@GetMapping("/public/auth/logout")
	public void logout(HttpSession session) {
		AS.logout(session);
	}
}
