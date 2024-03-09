package com.example.Auth;

import jakarta.persistence.*;

import java.util.Date;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(Integer isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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
