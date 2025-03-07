package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.Sessions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<Sessions, Long> {
    @Query("select s from Sessions s where s.user.username=?1 and s.token=?2")
    Optional<Sessions> findByUsernameAndToken(String username, String token);
}
