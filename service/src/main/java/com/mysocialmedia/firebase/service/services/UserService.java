package com.mysocialmedia.firebase.service.services;

import com.mysocialmedia.firebase.service.models.dtos.*;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    TokenDto login(LoginDto loginDto);
    TokenDto register(RegisterDto registerDto);
    ShowFullUserDto viewUserInfo();
    ShowFullUserDto updateUserInfo(UpdateUserInfoDto userInfoDto);
    FirebaseDto updateProfilePicture(MultipartFile multipartFile);
    OnlyTitleUserDto viewUserHeader();
}
