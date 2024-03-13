package com.example.Auth.Config;


import com.example.Auth.Model.AuthUser;
import com.example.Auth.Repository.AuthUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthFilter extends AuthenticationFilter {

    public JwtAuthFilter(AuthenticationManager authenticationManager, JwtAuthenticationConverter jwtAuthenticationConverter) {

        super(authenticationManager, jwtAuthenticationConverter);
        super.setFailureHandler(JwtAuthFilter::onFailure);
        super.setSuccessHandler(JwtAuthFilter::onSuccess);
    }


    private static void onFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex) throws IOException {
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setCharacterEncoding("utf-8");
        res.getWriter().println("You are not Mr Robot... 🤖");
    }

    private static void onSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException {
        // noop
    }

}


//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//            username = jwtService.extractUsername(token);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            if (jwtService.validateToken(token)) {
//
//                 final JwtDecoder jwtDecoder;
//                 jwtDecoder.decode(token)
//                JwtAuthenticationToken authToken = new JwtAuthenticationToken(new Jwt());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}

