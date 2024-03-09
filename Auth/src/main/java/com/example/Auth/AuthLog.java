package com.example.Auth;

import jakarta.persistence.*;

import java.util.Date;

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

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectInfo() {
        return objectInfo;
    }

    public void setObjectInfo(String objectInfo) {
        this.objectInfo = objectInfo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
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
