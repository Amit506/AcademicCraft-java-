package com.example.Auth.Controller;


import com.example.Auth.Service.AuthService;
import com.example.Auth.Model.AuthUser;
import com.example.Auth.Service.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthServiceImpl authService;


    @GetMapping("/test")
    public ResponseEntity signup(HttpServletRequest request) {
//        List<AuthUser> authService1= authService.getAuthUsers();
        System.out.println("hii");

        return ResponseEntity.ok("Hiii");
    }
}
