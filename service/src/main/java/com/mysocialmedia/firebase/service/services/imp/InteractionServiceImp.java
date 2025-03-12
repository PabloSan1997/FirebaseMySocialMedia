package com.mysocialmedia.firebase.service.services.imp;

import com.mysocialmedia.firebase.service.exceptions.MyBadRequestException;
import com.mysocialmedia.firebase.service.models.dtos.CommentDto;
import com.mysocialmedia.firebase.service.models.dtos.LikeDto;
import com.mysocialmedia.firebase.service.models.dtos.OnlyTitleUserDto;
import com.mysocialmedia.firebase.service.models.dtos.ShowCommentDto;
import com.mysocialmedia.firebase.service.models.entities.Comments;
import com.mysocialmedia.firebase.service.models.entities.Imagenes;
import com.mysocialmedia.firebase.service.models.entities.LikeEntity;
import com.mysocialmedia.firebase.service.models.entities.Users;
import com.mysocialmedia.firebase.service.repositories.CommentRepository;
import com.mysocialmedia.firebase.service.repositories.ImagenRespository;
import com.mysocialmedia.firebase.service.repositories.LikeRepository;
import com.mysocialmedia.firebase.service.repositories.UserRepository;
import com.mysocialmedia.firebase.service.services.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InteractionServiceImp implements InteractionService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private ImagenRespository imagenRespository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public LikeDto generateLike(Long idImage) {
        Users user = getAuthenticationUser();
        Imagenes imagen = imagenRespository.findById(idImage)
                .orElseThrow(()-> new MyBadRequestException("Id invalid"));
        Optional<LikeEntity> oLike = likeRepository.findByImageAndUsername(idImage, user.getUsername());
        if(oLike.isPresent()){
            likeRepository.deleteById(oLike.get().getId());
            return LikeDto.builder()
                    .idImage(idImage).userLike(false).build();
        }
        LikeEntity like = LikeEntity.builder()
                .user(user).imagen(imagen).build();
        likeRepository.save(like);
        return LikeDto.builder()
                .userLike(true).idImage(idImage).build();
    }

    @Override
    @Transactional
    public ShowCommentDto saveComment(Long idImage, CommentDto commentDto) {
        Imagenes imagen = imagenRespository.findById(idImage)
                .orElseThrow(()-> new MyBadRequestException("Id invalido"));
        Users user = getAuthenticationUser();
        OnlyTitleUserDto titleUser = OnlyTitleUserDto.builder()
                .username(user.getUsername())
                .fullname(user.getFullname())
                .urlImage(user.getUserInfo().getUrlImage()).build();
        Comments precomment = Comments.builder()
                .comment(commentDto.getComment())
                .imagen(imagen)
                .user(user).build();
        Comments comment = commentRepository.save(precomment);
        return ShowCommentDto.builder()
                .user(titleUser).comment(comment.getComment())
                .createAt(comment.getCreateAt()).id(comment.getId()).build();
    }

    @Override
    @Transactional
    public void deleteComment(Long idComment) {
        Users user = getAuthenticationUser();
        commentRepository.findByIdAndUsernmae(idComment, user.getUsername())
                .ifPresent(p -> {
                    commentRepository.deleteById(p.getId());
                });
    }

    private Users getAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Error con la autenticacion de usuario"));
    }
}
