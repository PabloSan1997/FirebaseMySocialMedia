package com.mysocialmedia.firebase.service.services;

import com.mysocialmedia.firebase.service.models.dtos.LikeDto;

public interface InteractionService {
    LikeDto generateLike(Long idImage);
}
