package com.chess.vikas.controller;

import com.chess.vikas.dto.LoginRequest;
import com.chess.vikas.dto.RegisterRequest;
import com.chess.vikas.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.chess.vikas.service.JwtService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;   // 👈 ADD THIS

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request, jwtService);
    }

    @GetMapping("/api/test/protected")
public String protectedEndpoint() {
    return "You accessed protected API!";
}
}