package com.example.Auth.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "auth_permission")
public class AuthPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String name;

    @Column(length = 100)
    private String codename;

    @Column(name = "created_at",nullable = false,updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at",nullable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedAt;
// Getters and setters
}
