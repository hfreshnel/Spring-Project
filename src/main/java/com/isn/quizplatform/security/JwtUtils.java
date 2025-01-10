package com.isn.quizplatform.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long EXPIRATION_TIME = 86400000; // 1 jour

    private Key key;

    @PostConstruct
    public void init() {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("La clé secrète JWT n'est pas définie dans les propriétés");
        }
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // Générer un token JWT
    public String generateToken(String mail, int role) {
        return Jwts.builder()
            .setClaims(Map.of("role", role)) // Ajouter les claims
            .setSubject(mail) // Définir l'email comme sujet (après setClaims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }


    // Valider un token JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extraire le rôle depuis le token
    public String extractRole(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        int roleValue = claims.get("role", Integer.class);

        return switch (roleValue) {
            case 0 -> "ROLE_PUBLIC";
            case 1000 -> "ROLE_ADMIN";
            default -> throw new IllegalArgumentException("Rôle inconnu : " + roleValue);
        };
    }

    // Extraire l'email (subject) depuis le token
    public String extractEmail(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        
        String email = claims.getSubject(); // Récupérer l'email depuis le subject
        System.out.println("Email extrait du token: " + email);
        return email;
    }
}
