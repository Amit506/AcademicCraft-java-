package com.example.Auth.Model;

import com.example.Auth.Model.AuthUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    // Getters and setters

    // Additional relationships or methods as needed
}
