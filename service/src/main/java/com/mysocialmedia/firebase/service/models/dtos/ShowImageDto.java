package com.mysocialmedia.firebase.service.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowImageDto {
    private Long id;
    private String urlImage;
    private String description;
    private Integer likes;
    private Integer comments;
    private Date createAt;
    private Boolean userLike;
    private OnlyTitleUserDto user;
}
