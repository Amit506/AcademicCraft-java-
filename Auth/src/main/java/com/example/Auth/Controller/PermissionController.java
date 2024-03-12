package com.example.Auth.Controller;

import com.example.Auth.Config.JwtAuthenticationProvider;
import com.example.Auth.RequestModel.PermissionRequest;
import com.example.Auth.RequestModel.SignupRequest;
import com.example.Auth.ResponseModel.BaseResponseBuilder;
import com.example.Auth.ResponseModel.SignUpResponse;
import com.example.Auth.Service.PermissionServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    private static final Logger LOG = LogManager.getLogger(PermissionController.class);

    @Autowired
    PermissionServiceImpl permissionService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add_permission")
    ResponseEntity addPermission(HttpServletRequest httpServletRequest,@RequestBody PermissionRequest permissionRequest){
        try{
            LOG.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
           BaseResponseBuilder baseResponseBuilder = permissionService.addPermission(permissionRequest);
            return  ResponseEntity.status(HttpStatus.valueOf(baseResponseBuilder.getStatusCode())).body(baseResponseBuilder);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add_group")
    ResponseEntity addGroup(HttpServletRequest httpServletRequest,@RequestBody PermissionRequest permissionRequest){
        try{
            BaseResponseBuilder baseResponseBuilder = permissionService.addGroup(permissionRequest);
            return  ResponseEntity.status(HttpStatus.valueOf(baseResponseBuilder.getStatusCode())).body(baseResponseBuilder);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/attach_group_permission")
    ResponseEntity attachGroupToPermission(HttpServletRequest httpServletRequest,@RequestParam Long groupId,@RequestParam Long permissionId) {
        try {
            BaseResponseBuilder baseResponseBuilder = permissionService.attachGroupToPermission(groupId, permissionId);
            return ResponseEntity.status(HttpStatus.valueOf(baseResponseBuilder.getStatusCode())).body(baseResponseBuilder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin_permission")
    public ResponseEntity adminPermission(HttpServletRequest request, @RequestParam Integer userId) {
        try{
            BaseResponseBuilder baseResponseBuilder= permissionService.provideAdminPermission(userId);
            return  ResponseEntity.status(HttpStatus.valueOf(baseResponseBuilder.getStatusCode())).body(baseResponseBuilder);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
    @PostMapping("/default_permission")
    public ResponseEntity defaultPermission(HttpServletRequest request, @RequestParam Integer userId) {
        try{
            BaseResponseBuilder baseResponseBuilder= permissionService.provideDefaultPermission(userId);
            return  ResponseEntity.status(HttpStatus.valueOf(baseResponseBuilder.getStatusCode())).body(baseResponseBuilder);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
