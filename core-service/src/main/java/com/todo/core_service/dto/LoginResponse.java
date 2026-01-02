package com.todo.core_service.dto;

public class LoginResponse {
    private String fullName;

    public LoginResponse(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
