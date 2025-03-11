package com.mysocialmedia.firebase.service.controllers;

import com.mysocialmedia.firebase.service.services.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interaction")
public class InteractionController {
    @Autowired
    private InteractionService interactionService;

    @PostMapping("/like/{idImage}")
    public ResponseEntity<?> generateLike(@PathVariable("idImage") Long id){
        return ResponseEntity.ok(interactionService.generateLike(id));
    }
}
