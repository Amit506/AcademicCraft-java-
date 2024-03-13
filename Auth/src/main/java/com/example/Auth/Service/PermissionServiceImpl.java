package com.example.Auth.Service;

import com.example.Auth.Config.UserRole;
import com.example.Auth.RequestModel.PermissionRequest;
import com.example.Auth.Model.*;
import com.example.Auth.Repository.*;
import com.example.Auth.ResponseModel.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImpl {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    AuthGroupRepository authGroupRepository;

    @Autowired
    AuthGroupPermissionRepository authGroupPermissionRepository;

    @Autowired
    AuthUserUserPermissionsRepository authUserUserPermissionsRepository;

    @Autowired
    AuthUserRepository authUserRepository;

    public BaseResponseBuilder addPermission(PermissionRequest permissionRequest) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setCodename(permissionRequest.getCodename());
        authPermission.setName(permissionRequest.getName());
        System.out.println(authPermission.toString());
        AuthPermission authPermissionObj = permissionRepository.save(authPermission);
        return BaseResponseBuilder.builder().statusCode(200).message("Added successfully").build();

    }

    public BaseResponseBuilder addGroup(PermissionRequest permissionRequest) {
        AuthGroup authGroup = new AuthGroup();
        authGroup.setName(permissionRequest.getName());
        AuthGroup authPermissionObj = authGroupRepository.save(authGroup);
        return BaseResponseBuilder.builder().statusCode(200).message("Added successfully").build();

    }

    public BaseResponseBuilder attachGroupToPermission(Long groupId, Long permissionId) {
        AuthGroupPermissions authGroupPermissions = new AuthGroupPermissions();
        authGroupPermissions.setPermission(permissionRepository.getReferenceById(permissionId));
        authGroupPermissions.setGroup(authGroupRepository.getReferenceById(groupId));
        authGroupPermissionRepository.save(authGroupPermissions);
        return BaseResponseBuilder.builder().statusCode(200).message("Added successfully").build();

    }

    public BaseResponseBuilder provideAdminPermission(Integer userId) {
        Optional<AuthUser> authUser = authUserRepository.findById(Long.valueOf(userId));
        authUser.ifPresent(this::assignPermissionToAdmin);
        return BaseResponseBuilder.builder().statusCode(200).message("Added successfully").build();

    }

    public BaseResponseBuilder provideDefaultPermission(Integer userId) {
        Optional<AuthUser> authUser = authUserRepository.findById(Long.valueOf(userId));
        authUser.ifPresent(this::assignPermissionToUser);
        return BaseResponseBuilder.builder().statusCode(200).message("Added successfully").build();

    }

    private void assignPermissionToUser(AuthUser user) {
        AuthPermission authPermission = permissionRepository.getReferenceById(Long.valueOf(UserRole.ROLE_DEFAULT.getRoleId()));
        AuthUserUserPermissions authUserUserPermissions = new AuthUserUserPermissions();
        authUserUserPermissions.setPermission(authPermission);
        authUserUserPermissions.setUser(user);
        AuthUserUserPermissions authUserUserPermissionsObj = authUserUserPermissionsRepository.save(authUserUserPermissions);
    }

    private void assignPermissionToAdmin(AuthUser user) {
        AuthPermission authPermission = permissionRepository.getReferenceById(Long.valueOf(UserRole.ROLE_ADMIN.getRoleId()));
        AuthUserUserPermissions authUserUserPermissions = new AuthUserUserPermissions();
        authUserUserPermissions.setPermission(authPermission);
        authUserUserPermissions.setUser(user);
        AuthUserUserPermissions authUserUserPermissionsObj = authUserUserPermissionsRepository.save(authUserUserPermissions);
    }

}
