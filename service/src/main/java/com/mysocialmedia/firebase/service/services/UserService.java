package com.mysocialmedia.firebase.service.services;

import com.mysocialmedia.firebase.service.models.dtos.LoginDto;
import com.mysocialmedia.firebase.service.models.dtos.RegisterDto;
import com.mysocialmedia.firebase.service.models.dtos.TokenDto;

public interface UserService {
    TokenDto login(LoginDto loginDto);
    TokenDto register(RegisterDto registerDto);

}
