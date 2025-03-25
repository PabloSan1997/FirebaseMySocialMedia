package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.Imagenes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ImagenRespository extends CrudRepository<Imagenes, Long> {

    @Query("select i from Imagenes i order by i.createAt desc")
    List<Imagenes> findAllOrderByDate(Pageable pageable);

    @Query("select i from Imagenes i where i.user.username = ?1 and i.id=?2")
    Optional<Imagenes> findByUsernameAndIdImage(String username, Long idImage);

    @Query("select i from Imagenes i where i.user.username=?1 order by i.createAt desc")
    List<Imagenes> findAllByUsername(String username, Pageable pageable);

    @Query("select i from Imagenes i join i.user.followers f where f.mainUser.username = ?1 order by i.createAt desc")
    List<Imagenes> findAllByFollows(String username, Pageable pageable);
}