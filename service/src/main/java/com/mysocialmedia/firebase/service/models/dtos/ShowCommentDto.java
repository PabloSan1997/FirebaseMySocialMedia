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
public class ShowCommentDto {
    private Long id;
    private String comment;
    private Date createAt;
    private OnlyTitleUserDto user;
}
