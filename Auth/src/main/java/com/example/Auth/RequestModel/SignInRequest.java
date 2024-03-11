package com.example.Auth.RequestModel;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NonNull;

@Data
public class SignInRequest {

    @NonNull
    private String name;
    private String username;
    @Email
    private String email;
    private String password;
    private String phoneNumber;

}
