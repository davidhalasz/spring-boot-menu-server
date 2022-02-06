package com.menu.menuserver.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "password",nullable = false, length = 100)
    private String password;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
}
