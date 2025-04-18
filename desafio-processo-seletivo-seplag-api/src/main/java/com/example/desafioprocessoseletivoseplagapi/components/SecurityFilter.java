package com.example.desafioprocessoseletivoseplagapi.components;

import com.example.desafioprocessoseletivoseplagapi.configs.SecurityConfig;
import com.example.desafioprocessoseletivoseplagapi.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenManagment tokenManagment;
    private final UserRepository repository;

    public SecurityFilter(TokenManagment tokenManagment, UserRepository repository) {
        this.tokenManagment = tokenManagment;
        this.repository = repository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!isPublicUri(request.getRequestURI())) {
            String token = recoverToken(request);
            if (token != null && !token.isEmpty()) {
                String username = tokenManagment.validadeToken(token);
                UserDetails user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isPublicUri(String uri) {
        for (String publicUri : SecurityConfig.PUBLIC_URIS) {
            if (uri.contains(publicUri)) {
                return true;
            }
        }
        return false;
    }

    private String recoverToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || header.isEmpty()) {
            return null;
        }
        return header.replace("Bearer ", "");
    }
}
