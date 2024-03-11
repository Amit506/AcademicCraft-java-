package com.example.Auth.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "auth_session")
public class AuthSession {

    @Id
    @Column(name = "session_key", length = 40)
    private String sessionKey;

    @Lob
    @Column(name = "session_data")
    private String sessionData;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_date")
    private Date expireDate;
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
// Getters and setters
}
