package com.mysocialmedia.firebase.service.models.dtos;

import com.mysocialmedia.firebase.service.models.entities.UserInfo;
import com.mysocialmedia.firebase.service.models.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowFullUserDto {
    private String username;
    private String fullname;
    private String description;
    private String urlImage;
    private Date born;
    private Date createCount;

    public ShowFullUserDto(Users user, UserInfo userInfo){
        this.username = user.getUsername();
        this.fullname = user.getFullname();
        this.description = userInfo.getDescription();
        this.urlImage = userInfo.getUrlImage();
        this.born = userInfo.getBorn();
        this.createCount = userInfo.getCreateCount();
    }
}
