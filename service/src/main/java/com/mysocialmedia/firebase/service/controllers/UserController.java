package com.mysocialmedia.firebase.service.controllers;

import com.mysocialmedia.firebase.service.models.dtos.LoginDto;
import com.mysocialmedia.firebase.service.models.dtos.RegisterDto;
import com.mysocialmedia.firebase.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/userinfo")
    public ResponseEntity<?> findMyUserInfo(){
        return ResponseEntity.ok(userService.viewUserInfo());
    }
    @GetMapping("/headeruser")
    public ResponseEntity<?> getHeaderUser(){
        return ResponseEntity.ok(userService.viewUserHeader());
    }

}
