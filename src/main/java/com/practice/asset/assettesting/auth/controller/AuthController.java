package com.practice.asset.assettesting.auth.controller;

import com.practice.asset.assettesting.model.AuthResponse;
import com.practice.asset.assettesting.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam String userName) {
        AuthResponse response = new AuthResponse();
        // Authenticate user (this is a simple example, add your authentication logic)
        // If authentication is successful, generate a token
        String token = jwtUtil.generateToken(userName);
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token, @RequestParam String userName) {
        boolean isValid = jwtUtil.validateToken(token, userName);
        return ResponseEntity.ok(isValid);
    }
}

