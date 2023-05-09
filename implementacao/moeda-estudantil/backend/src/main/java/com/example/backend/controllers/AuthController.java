package com.example.backend.controllers;

import com.example.backend.model.dto.LoginDto;
import com.example.backend.model.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return authService.authUser(loginDto);
    }
}
