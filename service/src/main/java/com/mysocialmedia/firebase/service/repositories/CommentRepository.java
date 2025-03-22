package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.Comments;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comments, Long> {

    @Query("select count(c) from Comments c where c.imagen.id=?1")
    Integer countCommentsById(Long idImage);

    @Query("select c from Comments c where c.imagen.id=?1 order by c.createAt desc")
    List<Comments> findAllByIdImage(Long idImage, Pageable pageable);

    @Query("select c from Comments c where c.id=?1 and c.user.username=?2")
    Optional<Comments> findByIdAndUsernmae(Long idComment, String username);

}
