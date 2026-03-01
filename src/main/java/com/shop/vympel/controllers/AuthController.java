package com.shop.vympel.controllers;

import com.shop.vympel.dtos.auth.AuthResponse;
import com.shop.vympel.dtos.auth.LoginByEmailRequest;
import com.shop.vympel.dtos.auth.RegisterByEmailRequest;
import com.shop.vympel.services.auth.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @Autowired
    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @PostMapping("/register/email")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid RegisterByEmailRequest req
    ) throws IllegalArgumentException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authServiceImpl.register(req));
    }

    @PostMapping("/login/email")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginByEmailRequest req
    ) throws IllegalArgumentException {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authServiceImpl.login(req));
    }
}
