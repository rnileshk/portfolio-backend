package com.example.nilesh.controller;

import com.example.nilesh.dto.LoginRequest;
import com.example.nilesh.entity.Admin;
import com.example.nilesh.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthController(AdminRepository adminRepository, BCryptPasswordEncoder encoder) {
        this.adminRepository = adminRepository;
        this.encoder = encoder;
    }

    // Create admin using Postman
    @PostMapping("/create-admin")
    public Map<String, String> createAdmin(@RequestBody LoginRequest request) {

        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            return Map.of("message", "Admin already exists");
        }

        Admin admin = new Admin();
        admin.setEmail(request.getEmail());
        admin.setPassword(encoder.encode(request.getPassword()));

        adminRepository.save(admin);

        return Map.of("message", "Admin created successfully");
    }

    // Admin login
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {

        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!encoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return Map.of(
                "message", "Login successful",
                "admin", true
        );
    }
}