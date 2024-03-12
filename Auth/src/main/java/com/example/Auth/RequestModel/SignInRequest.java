package com.example.Auth.RequestModel;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NonNull;

@Data
public class SignInRequest {


    private String username;
    private String password;

}
