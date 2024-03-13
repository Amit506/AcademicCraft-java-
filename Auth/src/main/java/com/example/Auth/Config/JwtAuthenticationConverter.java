package com.example.Auth.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtAuthenticationConverter implements AuthenticationConverter {
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationConverter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        String token = request.getHeader("x-accessToken");
        if (Objects.nonNull(token)) {
            JwtService jwtService = new JwtService(token);

            DefaultOAuth2AuthenticatedPrincipal authenticatedPrincipal = new DefaultOAuth2AuthenticatedPrincipal(jwtService.getClaimsMap(), Collections.emptyList());
            OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, token, jwtService.extractIssuedAt().toInstant(), jwtService.extractExpiration().toInstant());
            BearerTokenAuthentication bearerTokenAuthentication = new BearerTokenAuthentication(authenticatedPrincipal, oAuth2AccessToken, Collections.emptyList());
            return authenticationManager.authenticate(bearerTokenAuthentication);
        }
        return null;
    }
}
