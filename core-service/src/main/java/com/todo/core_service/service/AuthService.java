package com.todo.core_service.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.todo.core_service.dto.LoginRequest;
import com.todo.core_service.dto.RegisterRequest;
import com.todo.core_service.entity.User;
import com.todo.core_service.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {
        if(userRepository.findByEmail(request.email).isPresent()) {
            return "Email already exists";
        }
        User user = new User();
        user.setFullName(request.fullName);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.email);
        if(userOpt.isEmpty() || !passwordEncoder.matches(request.password, userOpt.get().getPassword())) {
            return "Invalid email or password";
        }
        return "Login successful";
    }
}
