package com.mysocialmedia.firebase.service.services.imp;

import com.mysocialmedia.firebase.service.exceptions.MyBadRequestException;
import com.mysocialmedia.firebase.service.models.dtos.FollowingCounts;
import com.mysocialmedia.firebase.service.models.dtos.OnlyTitleUserDto;
import com.mysocialmedia.firebase.service.models.dtos.ShowFullUserDto;
import com.mysocialmedia.firebase.service.models.dtos.ViewFollow;
import com.mysocialmedia.firebase.service.models.entities.Follows;
import com.mysocialmedia.firebase.service.models.entities.UserInfo;
import com.mysocialmedia.firebase.service.models.entities.Users;
import com.mysocialmedia.firebase.service.repositories.FollowRepository;
import com.mysocialmedia.firebase.service.repositories.UserRepository;
import com.mysocialmedia.firebase.service.services.FriendServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FriendServiceImp implements FriendServices {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public FollowingCounts mainUserFollowingCount() {
        Users user = getAuthenticationUser();
        Integer followings = followRepository.findFollowingsByUsername(user.getUsername());
        Integer followers = followRepository.findFollowersByUsername(user.getUsername());
        return FollowingCounts.builder()
                .followers(followers).followings(followings).build();
    }

    @Override
    @Transactional
    public FollowingCounts friendFollowingCount(String usernameFriend) {
        Integer followings = followRepository.findFollowingsByUsername(usernameFriend);
        Integer followers = followRepository.findFollowersByUsername(usernameFriend);
        return FollowingCounts.builder()
                .followers(followers).followings(followings).build();
    }

    @Override
    @Transactional
    public FollowingCounts generateFolling(String usernameFriend) {

        Users user = getAuthenticationUser();
        if(user.getUsername().equals(usernameFriend))
            throw new MyBadRequestException("Un usuario no se puede seguir asi mismo");

        Users userFriend = findFriendUser(usernameFriend);
        Optional<Follows> ofollow = followRepository.findUserAndFollowing(
                user.getUsername(),
                userFriend.getUsername()
        );
        if (ofollow.isPresent())
            followRepository.deleteById(ofollow.get().getId());
        else {
            Follows follows = Follows.builder()
                    .mainUser(user).followingUser(userFriend).build();
            followRepository.save(follows);
        }

        return friendFollowingCount(userFriend.getUsername());
    }

    @Override
    @Transactional
    public List<OnlyTitleUserDto> findFollows(String username, Boolean isFollowers, Pageable pageable) {
        if (isFollowers) {
            List<Follows> follows = followRepository.findAllFollowers(username, pageable);
            return follows.stream()
                    .map(p -> {
                        Users user = p.getMainUser();
                        UserInfo userInfo = user.getUserInfo();
                        return OnlyTitleUserDto.builder().username(user.getUsername())
                                .fullname(user.getFullname())
                                .urlImage(userInfo.getUrlImage()).build();
                    }).toList();
        } else {
            List<Follows> follows = followRepository.findAllFollowings(username, pageable);
            return follows.stream()
                    .map(p -> {
                        Users user = p.getFollowingUser();
                        UserInfo userInfo = user.getUserInfo();
                        return OnlyTitleUserDto.builder().username(user.getUsername())
                                .fullname(user.getFullname())
                                .urlImage(userInfo.getUrlImage()).build();
                    }).toList();

        }
    }

    @Override
    @Transactional
    public ShowFullUserDto findUserFriend(String username) {
        Users user = findFriendUser(username);
        UserInfo userInfo = user.getUserInfo();
        return new ShowFullUserDto(user, userInfo);
    }

    @Override
    @Transactional
    public ViewFollow viewFriendFollow(String username) {
        Users user = getAuthenticationUser();
        Optional<Follows> follows = followRepository.findUserAndFollowing(
                user.getUsername(),
                username
        );
        return ViewFollow.builder().viewFollow(follows.isPresent()).build();
    }


    private Users getAuthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No existe el usuario autenticado"));
    }

    private Users findFriendUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new MyBadRequestException("Usuario invalido"));
    }
}
