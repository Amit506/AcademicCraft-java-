package com.example.Auth.Config;

import com.example.Auth.Repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    AuthUserRepository authUserRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthentication bearerTokenAuthentication =(BearerTokenAuthentication) authentication;
        String token= bearerTokenAuthentication.getToken().getTokenValue();
        if ( SecurityContextHolder.getContext().getAuthentication() == null) {
            JwtService jwtService =new JwtService(token);
            if (jwtService.validateToken(token)) {
                String username= bearerTokenAuthentication.getName();
               return bearerTokenAuthentication;
            }
        }
        return  null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BearerTokenAuthentication.class.isAssignableFrom(authentication);
    }
}
