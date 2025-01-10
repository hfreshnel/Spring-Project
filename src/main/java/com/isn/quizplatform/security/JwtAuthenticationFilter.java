package com.isn.quizplatform.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (jwtUtils.validateToken(token)) {
                String mail = jwtUtils.extractEmail(token); // Récupérer l'email depuis le token
                String role = jwtUtils.extractRole(token); // Récupérer le rôle depuis le token

                // Créer une authentification avec l'email comme principal
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        mail, // Principal : email de l'utilisateur
                        null, // Credentials : non utilisé
                        Collections.singleton(new SimpleGrantedAuthority(role)) // Rôle
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continuer avec la chaîne de filtres
        filterChain.doFilter(request, response);
    }
}
