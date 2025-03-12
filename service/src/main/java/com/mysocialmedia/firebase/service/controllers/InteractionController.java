package com.mysocialmedia.firebase.service.controllers;

import com.mysocialmedia.firebase.service.models.dtos.CommentDto;
import com.mysocialmedia.firebase.service.services.InteractionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interaction")
public class InteractionController {
    @Autowired
    private InteractionService interactionService;

    @PostMapping("/like/{idImage}")
    public ResponseEntity<?> generateLike(@PathVariable("idImage") Long id){
        return ResponseEntity.ok(interactionService.generateLike(id));
    }
    @PostMapping("/comment/{idImage}")
    public ResponseEntity<?> generateComment(@RequestBody @Valid CommentDto commentDto, @PathVariable("idImage") Long id){
        var res = interactionService.saveComment(id, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    @DeleteMapping("/comment/{idComment}")
    public ResponseEntity<?> deleteComment(@PathVariable("idComment") Long id){
       interactionService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
