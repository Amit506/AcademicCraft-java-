package com.example.Auth.Controller;

import com.example.Auth.ResponseModel.BaseResponse;
import com.example.Auth.Service.TestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @PostMapping("/addUser")
    ResponseEntity attachGroupToPermission(HttpServletRequest httpServletRequest, @RequestParam String username, @RequestParam String password,@RequestParam String email) {
        try {
            BaseResponse baseResponse= testService.addTestUser(username, password,email);
            return ResponseEntity.status(HttpStatus.valueOf(200)).body(baseResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
