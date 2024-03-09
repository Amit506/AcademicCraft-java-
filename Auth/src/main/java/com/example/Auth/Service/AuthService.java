package com.example.Auth.Service;

import com.example.Auth.AuthUser;
import com.example.Auth.Repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    public List<AuthUser> getAuthUsers() {
        return authUserRepository.findAll();
    }


}
