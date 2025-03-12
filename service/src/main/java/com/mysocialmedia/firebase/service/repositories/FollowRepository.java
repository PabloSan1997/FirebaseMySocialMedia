package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.Follows;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends CrudRepository<Follows, Long> {

    @Query("select p from Follows p where p.mainUser.username = ?1 and p.followingUser.username=?2")
    Optional<Follows> findUserAndFollowing(String useranme1, String username2);

    @Query("select count(p) from Follows p where p.mainUser.username = ?1 ")
    Integer findFollowingsByUsername(String username);
    @Query("select count(p) from Follows p where p.followingUser.username=?1")
    Integer findFollowersByUsername(String username);

    @Query("select p from Follows p where p.mainUser.username=?1")
    List<Follows> findAllFollowings(String username, Pageable pageable);
    @Query("select p from Follows p where p.followingUser.username=?1")
    List<Follows> findAllFollowers(String username, Pageable pageable);
}
