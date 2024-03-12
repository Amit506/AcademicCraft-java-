package com.example.Auth.Service;

import com.example.Auth.RequestModel.SignInRequest;
import com.example.Auth.RequestModel.SignupRequest;
import com.example.Auth.ResponseModel.SignInResponse;
import com.example.Auth.ResponseModel.SignUpResponse;

public interface AuthService {

    public SignInResponse signIn (SignInRequest signInRequest);

    public SignUpResponse signUp(SignupRequest signupRequest);
    public  void signIn();

    public void addPermission();


}
