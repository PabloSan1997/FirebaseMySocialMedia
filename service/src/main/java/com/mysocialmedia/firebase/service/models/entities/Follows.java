package com.mysocialmedia.firebase.service.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "follows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Follows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user1")
    private Users mainUser;
    @ManyToOne
    @JoinColumn(name = "id_user2")
    private Users followingUser;

}
