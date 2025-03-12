package com.mysocialmedia.firebase.service.models.dtos;

import com.mysocialmedia.firebase.service.models.entities.Comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowOneImageDto {
    private Long id;
    private String urlImage;
    private String description;
    private Integer likes;
    private List<ShowCommentDto> comments;
    private Date createAt;
    private OnlyTitleUserDto user;
    private Boolean userLike;

    public ShowOneImageDto(ShowImageDto showImageDto, List<ShowCommentDto> comments){
        this.id = showImageDto.getId();
        this.urlImage = showImageDto.getUrlImage();
        this.description = showImageDto.getDescription();
        this.likes = showImageDto.getLikes();
        this.createAt = showImageDto.getCreateAt();
        this.user = showImageDto.getUser();
        this.comments = comments;
        this.userLike = showImageDto.getUserLike();
    }
}
