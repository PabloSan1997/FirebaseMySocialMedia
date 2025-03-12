package com.mysocialmedia.firebase.service.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    @NotBlank
    @Size(max = 300)
    private String comment;
}
