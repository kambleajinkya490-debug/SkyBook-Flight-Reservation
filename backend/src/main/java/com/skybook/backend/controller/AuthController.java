package com.skybook.backend.controller;

import com.skybook.backend.dto.*;
import com.skybook.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req){
        return authService.register(req);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req){
        return authService.login(req);
    }
}
