package com.chess.vikas.service;

import org.springframework.context.annotation.Profile;
import com.chess.vikas.dto.LoginRequest;
import com.chess.vikas.dto.RegisterRequest;
import com.chess.vikas.entity.User;
import com.chess.vikas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Profile("db")
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rating(1200)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequest request, JwtService jwtService) {

    User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    return jwtService.generateToken(user.getUsername());
}
}
