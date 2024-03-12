package com.example.Auth.Model;


import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "auth_group")
public class AuthGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 150)
    private String name;

    @Column(name = "created_at",nullable = false,updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at",nullable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedAt;
// Getters and setters
}
