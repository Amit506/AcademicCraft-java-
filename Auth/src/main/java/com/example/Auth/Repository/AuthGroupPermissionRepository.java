package com.example.Auth.Repository;

import com.example.Auth.Model.AuthGroup;
import com.example.Auth.Model.AuthGroupPermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthGroupPermissionRepository extends JpaRepository<AuthGroupPermissions, Long> {
    // Custom query methods, if needed
}
