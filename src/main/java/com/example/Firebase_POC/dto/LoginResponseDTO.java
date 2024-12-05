package com.example.Firebase_POC.dto;

public class LoginResponseDTO {
    private String userId;
    private String name;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
