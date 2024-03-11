package com.example.Auth.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Getter
@Setter
@Entity()
@Table(name = "auth_user")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 150)
    private String username;

    @Column(length = 128)
    private String password;

    @Column(length = 150)
    private String name;

    @Column(unique = true, length = 255)
    private String email;

    @Column(name = "phone_number", unique = true, length = 20)
    private String phoneNumber;

    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "is_superuser")
    private Integer isSuperuser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "created_at",nullable = false,updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at",nullable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedAt;
// Getters and setters
}
