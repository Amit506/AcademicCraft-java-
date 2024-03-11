package com.example.Auth.Service;

import com.example.Auth.RequestModel.PermissionRequest;
import com.example.Auth.Model.*;
import com.example.Auth.Repository.*;
import com.example.Auth.ResponseModel.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 public BaseResponse addPermission(PermissionRequest permissionRequest){
     AuthPermission authPermission=new AuthPermission();
     authPermission.setCodename(permissionRequest.getCodename());
     authPermission.setName(permissionRequest.getName());
     System.out.println(authPermission.toString());
     AuthPermission authPermissionObj=  permissionRepository.save(authPermission);
     return BaseResponse.builder().statusCode(200).message("Added successfully").build();

    }

    public BaseResponse addGroup(PermissionRequest permissionRequest){
        AuthGroup authGroup=new AuthGroup();
        authGroup.setName(permissionRequest.getName());
        AuthGroup authPermissionObj=  authGroupRepository.save(authGroup);
        return BaseResponse.builder().statusCode(200).message("Added successfully").build();

    }

    public  BaseResponse attachGroupToPermission(Long groupId, Long permissionId){
        AuthGroupPermissions authGroupPermissions = new AuthGroupPermissions();
        authGroupPermissions.setPermission(permissionRepository.getReferenceById(permissionId));
        authGroupPermissions.setGroup(authGroupRepository.getReferenceById(groupId));
        authGroupPermissionRepository.save(authGroupPermissions);
        return BaseResponse.builder().statusCode(200).message("Added successfully").build();

    }

    public void assignPermissionToUser(AuthUser user){
     AuthPermission authPermission = permissionRepository.getReferenceById(1L);
     AuthUserUserPermissions authUserUserPermissions =new AuthUserUserPermissions();
     authUserUserPermissions.setPermission(authPermission);
     authUserUserPermissions.setUser(user);
     AuthUserUserPermissions authUserUserPermissionsObj=authUserUserPermissionsRepository.save(authUserUserPermissions);
    }

}
