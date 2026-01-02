package com.todo.core_service.controller;

import com.todo.core_service.dto.ApiResponse;
import com.todo.core_service.dto.LoginResponse;
import com.todo.core_service.entity.User;
import com.todo.core_service.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ---------------- Register ----------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
        	return ResponseEntity
                    .status(409)
                    .body(new ApiResponse<>(409, "User with this email already exists", null));
        }
        User user = new User();
        user.setFullName(request.getFullName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        userRepository.save(user);

        return ResponseEntity
                .status(201)
                .body(new ApiResponse<>(201, "User registered successfully", null));
    }

    // ---------------- Login ----------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    	User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null ) {
        	return ResponseEntity
                    .status(401)
                    .body(new ApiResponse<>(401, "Invalid Email", null));
        }else if( !user.getPassword().equals(request.getPassword()) ) {
        	return ResponseEntity
                    .status(401)
                    .body(new ApiResponse<>(401, "Invalid Password", null));
        }
        LoginResponse response = new LoginResponse(user.getFullName());
        
        return ResponseEntity
                .status(200)
                .body(new ApiResponse<>(200, "Login successful", response));
    }

    // DTOs
    public static class RegisterRequest {
        private String fullName;
        private String password;
        private String email; // optional
        // getters & setters
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    public static class LoginRequest {
        private String email;
        private String password;
        // getters & setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
