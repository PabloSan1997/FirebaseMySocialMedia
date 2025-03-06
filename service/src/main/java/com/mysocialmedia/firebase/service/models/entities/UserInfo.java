package com.mysocialmedia.firebase.service.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 300)
    private String description;
    @Column(length = 500, name = "url_image")
    private String urlImage;
    @Column(unique = true, name = "imagefilename")
    private String imageFileName;
    private Date born;
    @Column(name = "create_at")
    private Date createCount;

    @PrePersist
    public void prepersist(){
        createCount = new Date();
    }

    @OneToOne
    @JoinColumn(name = "id_use")
    private Users user;
}
