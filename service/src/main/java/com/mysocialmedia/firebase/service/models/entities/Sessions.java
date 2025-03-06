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
@Table(name = "sessions")
public class Sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000, unique = true)
    private String token;
    @Column(name = "create_At")
    private Date createAt;
    @Column(name = "update_At")
    private Date updateAt;

    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;


    @PrePersist
    public void prepersist(){
        createAt = new Date();
        updateAt = new Date();
        state = true;
    }

    @PreUpdate
    public void preupdate(){
        updateAt = new Date();
    }
}
