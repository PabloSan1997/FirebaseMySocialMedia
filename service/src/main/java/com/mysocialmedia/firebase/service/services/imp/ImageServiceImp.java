package com.mysocialmedia.firebase.service.services.imp;

import com.mysocialmedia.firebase.service.exceptions.MyBadRequestException;
import com.mysocialmedia.firebase.service.models.dtos.FirebaseDto;
import com.mysocialmedia.firebase.service.models.dtos.OnlyTitleUserDto;
import com.mysocialmedia.firebase.service.models.dtos.ShowImageDto;
import com.mysocialmedia.firebase.service.models.dtos.ShowOneImageDto;
import com.mysocialmedia.firebase.service.models.entities.Comments;
import com.mysocialmedia.firebase.service.models.entities.Imagenes;
import com.mysocialmedia.firebase.service.models.entities.LikeEntity;
import com.mysocialmedia.firebase.service.models.entities.Users;
import com.mysocialmedia.firebase.service.repositories.*;
import com.mysocialmedia.firebase.service.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImp implements ImageService {
    @Autowired
    private ImagenRespository imagenRespository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FirebaseRepository firebaseRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Transactional
    public List<ShowImageDto> findAll() {
        List<Imagenes> imagenes = (List<Imagenes>) imagenRespository.findAll();
        Users user = getAuthenticationUser();
        return imagenes.stream()
                .map(im ->{
                   Integer likes = likeRepository.countLikesImage(im.getId());
                   Integer comments = commentRepository.countCommentsById(im.getId());
                   return changeShowImageDto(im, im.getUser(), likes, comments);
                }).toList();
    }

    @Override
    @Transactional
    public List<ShowImageDto> findByUsername(String username) {

        return imagenRespository.findAllByUsername(username).stream()
                .map(im -> {
                    Integer likes = likeRepository.countLikesImage(im.getId());
                    Integer comments = commentRepository.countCommentsById(im.getId());
                    return changeShowImageDto(im, im.getUser(), likes, comments);
                }).toList();
    }

    @Override
    @Transactional
    public List<ShowImageDto> findMainUser() {
        Users user = getAuthenticationUser();
        return imagenRespository.findAllByUsername(user.getUsername()).stream()
                .map(im -> {
                    Integer likes = likeRepository.countLikesImage(im.getId());
                    Integer comments = commentRepository.countCommentsById(im.getId());
                    return changeShowImageDto(im, user, likes, comments);
                }).toList();
    }

    @Override
    @Transactional
    public ShowImageDto saveImage(String description, MultipartFile multipartFile) {
        Users user = getAuthenticationUser();
        try{
            FirebaseDto firebaseDto = firebaseRepository.save(multipartFile);
            var imagenes = Imagenes.builder()
                    .description(description)
                    .imageFileName(firebaseDto.getFilename())
                    .urlImage(firebaseDto.getUrlImage())
                    .user(user).build();
            var newImage = imagenRespository.save(imagenes);
            return changeShowImageDto(newImage, user, 0, 0);
        }catch(Exception e){
            throw new MyBadRequestException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteImageById(Long id) {
        Users user = getAuthenticationUser();
        imagenRespository.findByUsernameAndIdImage(user.getUsername(), id).ifPresentOrElse(
                (im)->{
                    firebaseRepository.deleteImage(im.getImageFileName());
                    imagenRespository.deleteById(im.getId());
                },
                ()-> {
                    throw new MyBadRequestException("id invalido");
                }
        );
    }

    @Override
    @Transactional
    public ShowOneImageDto findImageById(Long id) {
        Imagenes imagenes = imagenRespository.findById(id)
                .orElseThrow(()-> new MyBadRequestException("Id invalido"));
        Integer likes = likeRepository.countLikesImage(id);
        List<Comments> comments = commentRepository.findAllByIdImage(id);
        ShowImageDto showImageDto = changeShowImageDto(imagenes, imagenes.getUser(), likes, 0);
        return new ShowOneImageDto(showImageDto, comments);

    }

    private Users getAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Error con la autenticacion de usuario"));
    }

    private ShowImageDto changeShowImageDto(Imagenes imagenes, Users user, Integer likes, Integer comments){
        Users authUser = getAuthenticationUser();
        Optional<LikeEntity> opLike = likeRepository.findByImageAndUsername(imagenes.getId(), authUser.getUsername());
        OnlyTitleUserDto onlyTitleUserDto = OnlyTitleUserDto.builder()
                .username(user.getUsername())
                .fullname(user.getFullname())
                .urlImage(user.getUserInfo().getUrlImage()).build();
        return ShowImageDto.builder()
                .id(imagenes.getId())
                .description(imagenes.getDescription())
                .urlImage(imagenes.getUrlImage())
                .likes(likes)
                .comments(comments)
                .createAt(imagenes.getCreateAt())
                .userLike(opLike.isPresent())
                .user(onlyTitleUserDto).build();
    }
}
