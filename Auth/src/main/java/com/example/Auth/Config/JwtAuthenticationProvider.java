package com.example.Auth.Config;

import com.example.Auth.Model.AuthUser;
import com.example.Auth.Model.AuthUserUserPermissions;
import com.example.Auth.Repository.AuthUserRepository;
import com.example.Auth.Repository.AuthUserUserPermissionsRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOG = LogManager.getLogger(JwtAuthenticationProvider.class);



    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    AuthUserUserPermissionsRepository authUserUserPermissionsRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthentication bearerTokenAuthentication =(BearerTokenAuthentication) authentication;
        String token= bearerTokenAuthentication.getToken().getTokenValue();
        if ( SecurityContextHolder.getContext().getAuthentication() == null) {
            JwtService jwtService =new JwtService(token);
            if (jwtService.validateToken(token)) {
                String username= bearerTokenAuthentication.getName();
                var roles=getRoles(username);
                LOG.info(roles);

                DefaultOAuth2AuthenticatedPrincipal authenticatedPrincipal = new DefaultOAuth2AuthenticatedPrincipal(jwtService.getClaimsMap(), new ArrayList<>(roles));
                OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, token, jwtService.extractIssuedAt().toInstant(), jwtService.extractExpiration().toInstant());
                return new BearerTokenAuthentication(authenticatedPrincipal, oAuth2AccessToken, roles);
            }
        }
        return  null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BearerTokenAuthentication.class.isAssignableFrom(authentication);
    }

     private Collection<? extends GrantedAuthority> getRoles(String username){
     AuthUser authUser= authUserRepository.findByUsername(username);
    List<AuthUserUserPermissions> authUserUserPermissions= authUserUserPermissionsRepository.findByUserId(authUser.getId());
   var roles= authUserUserPermissions.stream().map(authUserUserPermissions1 -> {
      String permissionName=  authUserUserPermissions1.getPermission().getCodename();
     return UserRole.valueOfOrElse(permissionName);
    }).toList();
  return roles.stream().map(userRole -> {
      return new SimpleGrantedAuthority(userRole.getRole());
   }).collect(Collectors.toList());


    }
}
