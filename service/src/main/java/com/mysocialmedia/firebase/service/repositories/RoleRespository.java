package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRespository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
