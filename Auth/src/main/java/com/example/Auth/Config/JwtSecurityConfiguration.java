package com.example.Auth.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@ComponentScan("com.example.Auth.Config")
@EnableMethodSecurity
@EnableWebSecurity
public class JwtSecurityConfiguration {
    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        var authManager = new ProviderManager(jwtAuthenticationProvider);
        SecurityFilterChain securityFilterChain = http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authConfig -> authConfig
                        .requestMatchers("/permission/**").authenticated()
                        .requestMatchers("/test/**").authenticated()
                        .anyRequest().permitAll())
                .authenticationProvider(jwtAuthenticationProvider)
                .addFilterBefore(new JwtAuthFilter(authManager, new JwtAuthenticationConverter(authManager)), UsernamePasswordAuthenticationFilter.class)

                .build();
        return securityFilterChain;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    static RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_DEFAULT");
        return hierarchy;
    }

}