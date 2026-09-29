package com.skybook.backend.service;

import com.skybook.backend.dto.LoginRequest;
import com.skybook.backend.dto.RegisterRequest;
import com.skybook.backend.entity.User;
import com.skybook.backend.repository.UserRepository;
import com.skybook.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    // REGISTER
    public String register(RegisterRequest req) {

        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            return "Email Already Registered";
        }

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .password(encoder.encode(req.getPassword()))
                .role("USER")
                .build();

        userRepo.save(user);

        return "User Registered Successfully";
    }

    // LOGIN
    public String login(LoginRequest req) {

        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}
