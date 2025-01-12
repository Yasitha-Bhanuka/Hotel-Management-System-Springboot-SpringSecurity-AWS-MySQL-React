package com.yasithadev.yasithadev.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class loginRequest {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}