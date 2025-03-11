package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.Imagenes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ImagenRespository extends CrudRepository<Imagenes, Long> {
    @Query("select i from Imagenes i where i.user.username = ?1 and i.id=?2")
    Optional<Imagenes> findByUsernameAndIdImage(String username, Long idImage);

    @Query("select i from Imagenes i where i.user.username=?1")
    List<Imagenes> findAllByUsername(String username);
}
