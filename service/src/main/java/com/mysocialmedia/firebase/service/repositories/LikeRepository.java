package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.LikeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LikeRepository extends CrudRepository<LikeEntity, Long> {
    @Query("select count(l) from LikeEntity l where l.imagen.id=?1")
    Integer countLikesImage(Long idImage);

    @Query("select l from LikeEntity l where l.imagen.id = ?1 and l.user.username = ?2")
    Optional<LikeEntity> findByImageAndUsername(Long idImage, String username);
}
