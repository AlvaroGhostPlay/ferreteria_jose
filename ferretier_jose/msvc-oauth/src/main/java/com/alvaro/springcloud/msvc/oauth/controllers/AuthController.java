package com.alvaro.springcloud.msvc.oauth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest req, HttpServletRequest request) {

        // DEBUG: imprime qué usuario y qué password viene
        System.out.println("LOGIN USER: [" + req.username() + "]");

        var auth = new UsernamePasswordAuthenticationToken(req.username(), req.password());

        try {
            Authentication result = authenticationManager.authenticate(auth);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(result);

            request.getSession(true).setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    context
            );
            request.getSession(true);
            // ...
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("AUTH ERROR: " + e.getClass().getName() + " -> " + e.getMessage());
            throw e;
        }
    }

    public record LoginRequest(String username, String password) {}
}