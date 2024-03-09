package com.example.Auth;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "auth_group_permissions")
public class AuthGroupPermissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private AuthGroup group;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private AuthPermission permission;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdat")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedat")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthGroup getGroup() {
        return group;
    }

    public void setGroup(AuthGroup group) {
        this.group = group;
    }

    public AuthPermission getPermission() {
        return permission;
    }

    public void setPermission(AuthPermission permission) {
        this.permission = permission;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Getters and setters
}
