package com.example.Auth.Repository;

import com.example.Auth.Model.ThirdPartyAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyAuthRepository extends JpaRepository<ThirdPartyAuth, Long> {
    // Custom query methods, if needed
}
