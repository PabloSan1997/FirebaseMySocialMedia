package com.mysocialmedia.firebase.service.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "likes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_at")
    private Date createAt;
    @ManyToOne
    @JoinColumn(name = "id_imagen")
    private Imagenes imagen;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;
}
