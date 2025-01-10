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
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http

                .authorizeHttpRequests(auth -> auth


                        .requestMatchers("public/questions/**","public/questions","/public/proposition/{id}", "/public/personnes/{id}","/public/quiz/**").hasRole("PUBLIC")
                        .requestMatchers("/admin/**","/admin/personnes/{id}","/admin/propositions/{id}","/admin/proposition/create","/admin/personnes").hasRole("ADMIN")
                        .requestMatchers("/v3/api-docs/**","/swagger-ui/**","/public/auth/register","/public/auth/login").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());

    return http.build();
    }
}
