package com.example.Auth;

import jakarta.persistence.*;

@Entity
@Table(name = "third_party_auth")
public class ThirdPartyAuth {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AuthUser user;

    @Column(length = 50)
    private String provider;

    @Column(name = "external_user_id", length = 255)
    private String externalUserId;

    @Column(length = 255)
    private String token;

    @Column(name = "is_verified")
    private Integer isVerified;

    @Column(name = "attribute_key", length = 255)
    private String attributeKey;

    @Column(name = "attribute_value", length = 255)
    private String attributeValue;

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    // Getters and setters

    // Additional relationships or methods as needed
}
