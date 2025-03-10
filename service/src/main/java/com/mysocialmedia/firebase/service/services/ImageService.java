package com.mysocialmedia.firebase.service.services;

import com.mysocialmedia.firebase.service.models.dtos.ShowImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<ShowImageDto> findAll();
    List<ShowImageDto> findByUsername();
    List<ShowImageDto> findMainUser();
    ShowImageDto saveImage(String description, MultipartFile multipartFile);
}
