package com.mysocialmedia.firebase.service.controllers;

import com.mysocialmedia.firebase.service.services.FriendServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friend")
public class FreindController {
    @Autowired
    private FriendServices friendServices;

    @GetMapping("/infouser/{username}")
    public ResponseEntity<?> findUserInfoFriend(@PathVariable String username){
        return ResponseEntity.ok(friendServices.findUserFriend(username));
    }

    @GetMapping("/mainfollows")
    public ResponseEntity<?> findMyFollows(){

        return ResponseEntity.ok(friendServices.mainUserFollowingCount());
    }

    @GetMapping("/followsfriend/{username}")
    public ResponseEntity<?> findFollowsFriend(@PathVariable String username){
        return ResponseEntity.ok(friendServices.friendFollowingCount(username));
    }

    @GetMapping("/followers/{username}")
    public ResponseEntity<?> followersUser(@PathVariable String username, Pageable pageable){
        return ResponseEntity.ok(friendServices.findFollows(username, true, pageable));
    }

    @GetMapping("/followings/{username}")
    public ResponseEntity<?> followingsUser(@PathVariable String username, Pageable pageable){
        return ResponseEntity.ok(friendServices.findFollows(username, false, pageable));
    }
    @GetMapping("/viewfollow/{username}")
    public ResponseEntity<?> viewFollos(@PathVariable String username){
        return ResponseEntity.ok(friendServices.viewFriendFollow(username));
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> generateFollowing(@PathVariable String username){
        return ResponseEntity.status(201).body(friendServices.generateFolling(username));
    }
}
