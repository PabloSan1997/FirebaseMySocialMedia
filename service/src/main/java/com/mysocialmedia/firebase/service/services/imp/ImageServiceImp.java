package com.mysocialmedia.firebase.service.services.imp;

import com.mysocialmedia.firebase.service.models.dtos.OnlyTitleUserDto;
import com.mysocialmedia.firebase.service.models.dtos.ShowImageDto;
import com.mysocialmedia.firebase.service.models.entities.Imagenes;
import com.mysocialmedia.firebase.service.models.entities.Users;
import com.mysocialmedia.firebase.service.repositories.ImagenRespository;
import com.mysocialmedia.firebase.service.repositories.UserRepository;
import com.mysocialmedia.firebase.service.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageServiceImp implements ImageService {
    @Autowired
    private ImagenRespository imagenRespository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<ShowImageDto> findAll() {
        List<Imagenes> imagenes = (List<Imagenes>) imagenRespository.findAll();
        return imagenes.stream()
                .map(im ->{
                    String urlImage = im.getUrlImage();
                    String description = im.getDescription();
                    Users user = im.getUser();
                    var onlyTitleUserDto = OnlyTitleUserDto.builder()
                            .username(user.getUsername())
                            .fullname(user.getFullname())
                            .urlImage(user.getUserInfo().getUrlImage()).build();
                    return ShowImageDto.builder().urlImage(urlImage).description(description)
                            .user(onlyTitleUserDto).build();
                }).toList();
    }

    @Override
    @Transactional
    public List<ShowImageDto> findByUsername() {
        return null;
    }

    @Override
    @Transactional
    public List<ShowImageDto> findMainUser() {
        return null;
    }

    @Override
    @Transactional
    public ShowImageDto saveImage(String description, MultipartFile multipartFile) {
        return null;
    }

    private Users getAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Error con la autenticacion de usuario"));
    }
}
