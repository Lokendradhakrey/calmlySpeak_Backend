package com.Lokenra.calmly_speak.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "my_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    @NotBlank(message = "username must be filled")
    private String username;
    @Column(name = "password")
    @NotBlank(message = "password must be filled")
    private String password;
    @Column(name = "email", unique = true)
    @NotBlank(message = "email must be filled")
    private String email;
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "connected_user_id")
    private int connectedUserId;
}
