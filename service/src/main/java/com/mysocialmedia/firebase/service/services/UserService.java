package com.mysocialmedia.firebase.service.services;

import com.mysocialmedia.firebase.service.models.dtos.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    TokenDto login(LoginDto loginDto);
    TokenDto register(RegisterDto registerDto);
    ShowFullUserDto viewUserInfo();
    ShowFullUserDto updateUserInfo(UpdateUserInfoDto userInfoDto);
    FirebaseDto updateProfilePicture(MultipartFile multipartFile);
    OnlyTitleUserDto viewUserHeader();
    void logout(String token);
}
