package com.example.Auth.Service;

import com.example.Auth.RequestModel.SignInRequest;
import com.example.Auth.ResponseModel.SignInResponse;

public interface AuthServiceImpl {

    public SignInResponse signUp (SignInRequest signInRequest);
    public  void signIn();

    public void addPermission();

}
