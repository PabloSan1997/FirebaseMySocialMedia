package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.Sessions;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Sessions, Long> {
}
