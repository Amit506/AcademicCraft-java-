package com.example.Auth.Controller;


import com.example.Auth.RequestModel.SignInRequest;
import com.example.Auth.RequestModel.SignupRequest;
import com.example.Auth.Model.AuthUser;
import com.example.Auth.ResponseModel.BaseResponse;
import com.example.Auth.ResponseModel.BaseResponseBuilder;
import com.example.Auth.ResponseModel.SignInResponse;
import com.example.Auth.ResponseModel.SignUpResponse;
import com.example.Auth.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity signup(HttpServletRequest request, @RequestBody SignupRequest signupRequest) {
        System.out.println("hii");
        try{
            SignUpResponse signUpResponse= authService.signUp(signupRequest);
            return  ResponseEntity.status(HttpStatus.valueOf(signUpResponse.getStatusCode())).body(signUpResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
    @PostMapping("/logIn")
    public ResponseEntity logIn(HttpServletRequest request, @RequestBody SignInRequest signInRequest) {
        try{
            SignInResponse signInResponse= authService.signIn(signInRequest);
            return  ResponseEntity.status(HttpStatus.valueOf(signInResponse.getStatusCode())).body(signInResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
