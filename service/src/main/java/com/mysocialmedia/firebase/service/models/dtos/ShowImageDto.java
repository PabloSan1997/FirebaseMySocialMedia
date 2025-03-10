package com.mysocialmedia.firebase.service.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowImageDto {
    private String urlImage;
    private String description;
    private OnlyTitleUserDto user;
}
