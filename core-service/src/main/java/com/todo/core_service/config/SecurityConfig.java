package com.todo.core_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 1️⃣ In-memory user for testing
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password("password")  // plain text for testing
                .roles("USER")
                .build());
        return manager;
    }

    // 2️⃣ Password encoder (plain text)
    @Bean
    public PasswordEncoder userPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // 3️⃣ SecurityFilterChain for Spring Security 6+
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for Postman testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // Allow login & register
                .anyRequest().authenticated()                // Other endpoints secured
            )
            .httpBasic(httpBasic -> {}); // Correct lambda-style syntax

        return http.build();
    }
}
