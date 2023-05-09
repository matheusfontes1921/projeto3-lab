package com.example.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String email;
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}