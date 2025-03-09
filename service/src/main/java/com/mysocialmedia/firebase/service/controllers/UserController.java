package com.mysocialmedia.firebase.service.controllers;

import com.mysocialmedia.firebase.service.models.dtos.LoginDto;
import com.mysocialmedia.firebase.service.models.dtos.RegisterDto;
import com.mysocialmedia.firebase.service.models.dtos.UpdateUserInfoDto;
import com.mysocialmedia.firebase.service.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(userService.register(registerDto));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.replace("Bearer ", "");
        userService.logout(token);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/imageprofile")
    public ResponseEntity<?> addUrlImage(@RequestParam("image") MultipartFile multipartFile){
        return ResponseEntity.ok(userService.updateProfilePicture(multipartFile));
    }
    @PostMapping("/profile")
    public ResponseEntity<?> addUrlImage(@RequestBody UpdateUserInfoDto updateUserInfoDto){
        return ResponseEntity.ok(userService.updateUserInfo(updateUserInfoDto));
    }
    @GetMapping("/userinfo")
    public ResponseEntity<?> findMyUserInfo(){
        return ResponseEntity.ok(userService.viewUserInfo());
    }
    @GetMapping("/headeruser")
    public ResponseEntity<?> getHeaderUser(){
        return ResponseEntity.ok(userService.viewUserHeader());
    }

}
