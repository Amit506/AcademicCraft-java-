package com.example.Auth.Controller;

import com.example.Auth.ResponseModel.BaseResponseBuilder;
import com.example.Auth.Service.TestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @PreAuthorize("hasRole('ROLE_DEFAULT')")
    @PostMapping("/addUser")
    ResponseEntity attachGroupToPermission(HttpServletRequest httpServletRequest, @RequestParam String username, @RequestParam String password, @RequestParam String email) {
        try {
            BaseResponseBuilder baseResponseBuilder = testService.addTestUser(username, password, email);
            return ResponseEntity.status(HttpStatus.valueOf(200)).body(baseResponseBuilder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PreAuthorize("hasRole('ROLE_DEFAULT')")
    @GetMapping("/ping")
    ResponseEntity test(HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.status(HttpStatus.valueOf(200)).body("Sucess");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
