package com.mysocialmedia.firebase.service.controllers;

import com.mysocialmedia.firebase.service.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(imageService.findAll(pageable));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> findUsername(@PathVariable String username, Pageable pageable) {
        return ResponseEntity.ok(imageService.findByUsername(username, pageable));
    }

    @GetMapping("/user")
    public ResponseEntity<?> findMainUser(Pageable pageable) {
        return ResponseEntity.ok(imageService.findMainUser(pageable));
    }

    @GetMapping("/{idImage}")
    public ResponseEntity<?> findByIdImage(@PathVariable("idImage") Long id, Pageable pageable){
        return ResponseEntity.ok(imageService.findImageById(id, pageable));
    }


    @PostMapping
    public ResponseEntity<?> save(
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile file
    ) {
        var res = imageService.saveImage(description, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id){
        imageService.deleteImageById(id);
        return ResponseEntity.noContent().build();
    }
}
