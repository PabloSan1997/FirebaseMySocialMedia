package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.Comments;
import com.mysocialmedia.firebase.service.models.entities.Imagenes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comments, Long> {

    @Query("select count(c) from Comments c where c.imagen.id=?1")
    Integer countCommentsById(Long idImage);

    @Query("select c from Comments c where c.imagen.id=?1")
    List<Comments> findAllByIdImage(Long idImage);

    @Query("select c from Comments c where c.id=?1 and c.user.username=?2")
    Optional<Comments> findByIdAndUsernmae(Long idComment, String username);

}
