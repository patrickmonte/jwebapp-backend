package com.jwebapp.jwebappbackend.controller;

import com.jwebapp.jwebappbackend.payload.request.LoginRequest;
import com.jwebapp.jwebappbackend.payload.request.RegisterRequest;
import com.jwebapp.jwebappbackend.payload.response.JwtResponse;
import com.jwebapp.jwebappbackend.repository.UserRepository;
import com.jwebapp.jwebappbackend.security.jwt.JwtUtils;
import com.jwebapp.jwebappbackend.security.services.UserDetailsServiceImpl;
import com.jwebapp.jwebappbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Assuming UserDetails has enough info, or fetch from repo if needed
        // For simplicity, we'll cast and rely on the username in UserDetails for now.
        // In a real app, you might fetch the full User entity here to get ID and email.
        
        // Placeholder values for id and email, as UserDetails doesn't directly expose them.
        // In a real application, you'd extend UserDetails or fetch the User entity.
        Long id = null; // You'll need to retrieve the user ID from the database
        String email = userDetails.getUsername(); // As a placeholder, using username as email for now

        return ResponseEntity.ok(new JwtResponse(jwt, id, userDetails.getUsername(), email));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.registerNewUser(registerRequest);
        return ResponseEntity.ok("User registered successfully!");
    }
}
