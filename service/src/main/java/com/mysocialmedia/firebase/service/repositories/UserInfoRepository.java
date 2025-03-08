package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
    @Query("select u from UserInfo u where u.user.username=?1")
    Optional<UserInfo> findByUsername(String username);
}
