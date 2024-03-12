package com.example.Auth.Service;

import com.example.Auth.Config.JwtService;
import com.example.Auth.Model.AuthUser;
import com.example.Auth.Repository.AuthUserRepository;
import com.example.Auth.ResponseModel.BaseResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestService {
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    AuthUserRepository authUserRepository;
  public BaseResponseBuilder addTestUser(String username, String password, String email){
        AuthUser authUser = new AuthUser();
        authUser.setEmail(email);
        authUser.setPassword(passwordEncoder.encode(password));
        authUser.setUsername(username);
        authUser.setName(username);
        authUser.setIsActive(1);
        authUser.setIsSuperuser(1);
        authUser.setPasswordHash(passwordEncoder.encode(password));
        authUserRepository.save(authUser);
      BaseResponseBuilder baseResponseBuilder = BaseResponseBuilder.builder().statusCode(200)
              .message(JwtService.generateToken(username, Collections.EMPTY_MAP)).build();
      return baseResponseBuilder;
    }
}
