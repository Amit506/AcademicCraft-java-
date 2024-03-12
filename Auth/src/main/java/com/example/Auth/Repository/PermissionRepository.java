package com.example.Auth.Repository;

import com.example.Auth.Model.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<AuthPermission, Long> {
    // Custom query methods, if needed

}