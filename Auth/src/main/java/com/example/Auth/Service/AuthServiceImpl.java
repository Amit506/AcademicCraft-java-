package com.example.Auth.Service;

import com.example.Auth.Config.JwtService;
import com.example.Auth.Model.ThirdPartyAuth;
import com.example.Auth.Repository.ThirdPartyAuthRepository;
import com.example.Auth.RequestModel.SignInRequest;
import com.example.Auth.Model.AuthUser;
import com.example.Auth.Repository.AuthUserRepository;
import com.example.Auth.RequestModel.SignupRequest;
import com.example.Auth.ResponseModel.SignInResponse;
import com.example.Auth.ResponseModel.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private ThirdPartyAuthRepository thirdPartyAuthRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AuthUser> getAuthUsers() {
        return authUserRepository.findAll();
    }
    @Override
    public SignUpResponse signUp(SignupRequest signupRequest) {
        String encodedPassword =passwordEncoder.encode(signupRequest.getPassword());
        AuthUser authUser = new AuthUser();
        authUser.setEmail(signupRequest.getEmail());
        authUser.setPassword(signupRequest.getPassword());
        authUser.setUsername(signupRequest.getUsername());
        authUser.setName(signupRequest.getName());
        authUser.setIsActive(1);
        authUser.setIsSuperuser(0);
        authUser.setPasswordHash(encodedPassword);
        AuthUser authUserResponse= authUserRepository.save(authUser);
        ThirdPartyAuth thirdPartyAuth = new ThirdPartyAuth();
        thirdPartyAuth.setUser(authUserResponse);
        thirdPartyAuth.setProvider("email");
        thirdPartyAuth.setIsVerified(0);
        thirdPartyAuthRepository.save(thirdPartyAuth);
        String accessToken=JwtService.generateToken(signupRequest.getUsername(), Collections.EMPTY_MAP);
        String refreshToken=JwtService.generateRefreshToken(signupRequest.getUsername());
        SignUpResponse signUpResponse =new SignUpResponse();
        signUpResponse.setAccessToken(accessToken);
        signUpResponse.setRefreshToken(refreshToken);
        signUpResponse.setStatusCode(200);
        signUpResponse.setUserId(authUserResponse.getId());
        return  signUpResponse;

    }


    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {

        String encodedPassword =passwordEncoder.encode(signInRequest.getPassword());
       AuthUser authUser= authUserRepository.findByUsername(signInRequest.getUsername());
       boolean passwordMatched =(passwordEncoder.matches(signInRequest.getPassword(),authUser.getPasswordHash()));
       if(passwordMatched){
           String accessToken=JwtService.generateToken(signInRequest.getUsername(), Collections.EMPTY_MAP);
           String refreshToken=JwtService.generateRefreshToken(signInRequest.getUsername());
           SignInResponse signInResponse =new SignInResponse();
           signInResponse.setAccessToken(accessToken);
           signInResponse.setRefreshToken(refreshToken);
           signInResponse.setStatusCode(200);
           signInResponse.setUserId(authUser.getId());
           return  signInResponse;
       }
        SignInResponse signInResponse =new SignInResponse();
        signInResponse.setMessage("failed to login");
        signInResponse.setStatusCode(400);
        return  signInResponse;

    }

    @Override
    public void signIn() {

    }

    @Override
    public void addPermission() {

    }
}
