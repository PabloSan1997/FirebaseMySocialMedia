package com.mysocialmedia.firebase.service.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @Column(name = "create_At")
    private Date createAt;
    @ManyToOne
    @JoinColumn(name = "id_imagen")
    private Imagenes imagen;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;

    @PrePersist
    public void prepersist(){
        createAt = new Date();
    }
}
