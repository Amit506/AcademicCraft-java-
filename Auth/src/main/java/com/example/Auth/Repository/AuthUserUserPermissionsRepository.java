package com.example.Auth.Repository;

import com.example.Auth.Model.AuthPermission;
import com.example.Auth.Model.AuthUserUserPermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthUserUserPermissionsRepository extends JpaRepository<AuthUserUserPermissions, Long> {
    // Custom query methods, if needed
    List<AuthUserUserPermissions> findByUserId(Long userId);
}