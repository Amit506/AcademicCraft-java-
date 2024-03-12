package com.example.Auth.RequestModel;

import lombok.Data;

@Data
public class SignupRequest {
    String username;
    String name;
    String email;
    String password;
}
