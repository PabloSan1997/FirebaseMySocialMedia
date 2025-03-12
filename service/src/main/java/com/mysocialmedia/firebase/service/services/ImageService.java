package com.mysocialmedia.firebase.service.services;

import com.mysocialmedia.firebase.service.models.dtos.ShowImageDto;
import com.mysocialmedia.firebase.service.models.dtos.ShowOneImageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<ShowImageDto> findAll(Pageable pageable);
    List<ShowImageDto> findByUsername(String username, Pageable pageable);
    List<ShowImageDto> findMainUser(Pageable pageable);
    ShowImageDto saveImage(String description, MultipartFile multipartFile);
    void deleteImageById(Long id);
    ShowOneImageDto findImageById(Long id, Pageable pageableComments);
}
