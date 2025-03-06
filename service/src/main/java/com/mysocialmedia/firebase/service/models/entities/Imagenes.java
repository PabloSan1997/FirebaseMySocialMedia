package com.mysocialmedia.firebase.service.models.entities;

import jakarta.persistence.*;
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
@Entity
@Table(name = "imagenes")
public class Imagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "create_At")
    private Date createAt;
    @Column(name = "url_image", length = 555)
    private String urlImage;
    @Column(unique = true, name = "imagefilename")
    private String imageFileName;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;

    @OneToMany(mappedBy = "imagen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likes;

    @OneToMany(mappedBy = "imagen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments;

    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }
}
