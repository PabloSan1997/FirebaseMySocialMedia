package com.mysocialmedia.firebase.service.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
    @Size(max = 60) @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Size(max = 80) @NotBlank
    private String fullname;
}
