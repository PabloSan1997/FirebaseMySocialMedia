package com.mysocialmedia.firebase.service.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnlyTitleUserDto {
    private String username;
    private String fullname;
    private String urlImage;
}
