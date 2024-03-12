package com.example.Auth.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "auth_log")
public class AuthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "object_info", length = 200)
    private String objectInfo;

    @Column
    private String msg;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser user;

    @Column(name = "created_at",nullable = false,updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at",nullable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedAt;
// Getters and setters
}
