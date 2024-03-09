package com.example.Auth.Repository;

import com.example.Auth.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    // Custom query methods, if needed
}
