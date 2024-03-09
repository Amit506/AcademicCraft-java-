package com.example.Auth;

import jakarta.persistence.*;
import java.util.Date;

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

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionData() {
        return sessionData;
    }

    public void setSessionData(String sessionData) {
        this.sessionData = sessionData;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
// Getters and setters
}
