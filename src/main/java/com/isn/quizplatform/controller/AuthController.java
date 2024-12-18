package com.isn.quizplatform.controller;

import com.isn.quizplatform.model.ApiResponse;
import com.isn.quizplatform.model.AuthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.isn.quizplatform.model.LoginRequest;
import com.isn.quizplatform.model.Personne;
import com.isn.quizplatform.service.AuthService;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {

	@Autowired
	private AuthService AS;
	
	@PostMapping("/public/auth/register")
	public ApiResponse<Personne> register(@RequestBody Personne personne) {
		return AS.register(personne);
	}
	
	@PostMapping("/public/auth/login")
	public ApiResponse<AuthResponse> login(@RequestBody LoginRequest loginrequest) {
		return AS.login(loginrequest.getMail(), loginrequest.getMdp());
	}

}
