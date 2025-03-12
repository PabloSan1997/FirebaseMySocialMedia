package com.mysocialmedia.firebase.service.services;

import com.mysocialmedia.firebase.service.models.dtos.CommentDto;
import com.mysocialmedia.firebase.service.models.dtos.LikeDto;
import com.mysocialmedia.firebase.service.models.dtos.ShowCommentDto;

public interface InteractionService {
    LikeDto generateLike(Long idImage);
    ShowCommentDto saveComment(Long idImage, CommentDto commentDto);
    void deleteComment(Long idComment);
}
