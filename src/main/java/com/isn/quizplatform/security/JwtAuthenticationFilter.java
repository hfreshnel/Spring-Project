package com.isn.quizplatform.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collections;

/**
 * JwtAuthenticationFilter: A custom filter for processing JWT tokens.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    // Constructor injection for the JwtUtils utility class
    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

	@Override
	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
			throws jakarta.servlet.ServletException, IOException {
		 String authorizationHeader = request.getHeader("Authorization");

	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String token = authorizationHeader.substring(7);

	            if (jwtUtils.validateToken(token)) {
	                String role = jwtUtils.extractRole(token);

	                SecurityContextHolder.getContext().setAuthentication(
	                    new UsernamePasswordAuthenticationToken(
	                        null, 
	                        null, 
	                        Collections.singleton(new SimpleGrantedAuthority(role))
	                    )
	                );
	            }
	        }

	        filterChain.doFilter(request, response);
		
	}
    
}
