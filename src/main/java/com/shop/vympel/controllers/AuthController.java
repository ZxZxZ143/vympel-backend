package com.shop.vympel.controllers;

import com.shop.vympel.dtos.auth.AuthResponse;
import com.shop.vympel.dtos.auth.LoginByEmailRequest;
import com.shop.vympel.dtos.auth.RegisterByEmailRequest;
import com.shop.vympel.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/email")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid RegisterByEmailRequest req
    ) throws IllegalArgumentException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(req));
    }

    @PostMapping("/login/email")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginByEmailRequest req
    ) throws IllegalArgumentException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authService.login(req));
    }
}
