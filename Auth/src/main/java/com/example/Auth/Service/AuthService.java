package com.example.Auth.Service;

import com.example.Auth.RequestModel.SignInRequest;
import com.example.Auth.Model.AuthUser;
import com.example.Auth.Repository.AuthUserRepository;
import com.example.Auth.ResponseModel.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements AuthServiceImpl {

    @Autowired
    private AuthUserRepository authUserRepository;

    public List<AuthUser> getAuthUsers() {
        return authUserRepository.findAll();
    }


    @Override
    public SignInResponse signUp(SignInRequest signInRequest) {
        return  new SignInResponse();

    }

    @Override
    public void signIn() {

    }

    @Override
    public void addPermission() {

    }
}
