package com.example.Auth.Controller;

import com.example.Auth.RequestModel.PermissionRequest;
import com.example.Auth.ResponseModel.BaseResponse;
import com.example.Auth.Service.PermissionServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionServiceImpl permissionService;
    @PostMapping("/add_permission")
    ResponseEntity addPermission(HttpServletRequest httpServletRequest,@RequestBody PermissionRequest permissionRequest){
        try{
           BaseResponse baseResponse= permissionService.addPermission(permissionRequest);
            return  ResponseEntity.status(HttpStatus.valueOf(baseResponse.getStatusCode())).body(baseResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

    }
    @PostMapping("/add_group")
    ResponseEntity addGroup(HttpServletRequest httpServletRequest,@RequestBody PermissionRequest permissionRequest){
        try{
            BaseResponse baseResponse= permissionService.addGroup(permissionRequest);
            return  ResponseEntity.status(HttpStatus.valueOf(baseResponse.getStatusCode())).body(baseResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

    }
    @PostMapping("/attach_group_permission")
    ResponseEntity attachGroupToPermission(HttpServletRequest httpServletRequest,@RequestParam Long groupId,@RequestParam Long permissionId) {
        try {
            BaseResponse baseResponse = permissionService.attachGroupToPermission(groupId, permissionId);
            return ResponseEntity.status(HttpStatus.valueOf(baseResponse.getStatusCode())).body(baseResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
