package com.mysocialmedia.firebase.service.services;

import com.mysocialmedia.firebase.service.models.dtos.FollowingCounts;
import com.mysocialmedia.firebase.service.models.dtos.OnlyTitleUserDto;
import com.mysocialmedia.firebase.service.models.dtos.ShowFullUserDto;
import com.mysocialmedia.firebase.service.models.dtos.ViewFollow;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FriendServices {
    FollowingCounts mainUserFollowingCount();
    FollowingCounts friendFollowingCount(String usernameFriend);
    FollowingCounts generateFolling(String usernameFriend);
    List<OnlyTitleUserDto> findFollows(String username, Boolean isFollowers, Pageable pageable);
    ShowFullUserDto findUserFriend(String username);
    ViewFollow viewFriendFollow(String username);
}
