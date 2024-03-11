package com.example.Auth.Repository;

import com.example.Auth.Model.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
    // Custom query methods, if needed
}

