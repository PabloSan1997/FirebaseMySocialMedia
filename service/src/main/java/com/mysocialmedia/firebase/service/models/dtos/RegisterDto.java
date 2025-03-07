package com.mysocialmedia.firebase.service.models.dtos;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String fullname;
    private String password;
}
