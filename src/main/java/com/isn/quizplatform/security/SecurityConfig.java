package com.isn.quizplatform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            // Endpoints accessibles au public
            .requestMatchers(
                    "/public/auth/register", 
                    "/public/auth/login", 
                    "/public/personnes/{id}", 
                    "/public/quiz/**",  // Public pour les quiz
                    "/swagger-ui/**", 
                    "/v3/api-docs/**", "/admin/quiz/**", "/admin/**"
            ).permitAll()
            
            
            // Toute autre requête nécessite une authentification
            .anyRequest().authenticated()
    )
    .csrf(csrf -> csrf.disable());

    return http.build();
    }
}
