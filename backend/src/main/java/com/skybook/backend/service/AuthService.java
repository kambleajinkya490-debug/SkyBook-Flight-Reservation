package com.skybook.backend.service;

import com.skybook.backend.dto.*;
import com.skybook.backend.entity.User;
import com.skybook.backend.jwt.JwtUtil;
import com.skybook.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository repo, JwtUtil jwtUtil){
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegisterRequest req){

        if(repo.existsByEmail(req.getEmail()))
            return "Email already exists";

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role("USER")
                .build();

        repo.save(user);

        return "User Registered Successfully";
    }

    public LoginResponse login(LoginRequest req){

        User user = repo.findByEmail(req.getEmail())
                .orElse(null);

        if(user == null)
            return new LoginResponse(null,"User Not Found");

        if(!encoder.matches(req.getPassword(), user.getPassword()))
            return new LoginResponse(null,"Invalid Password");

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token,"Login Success");
    }
}
