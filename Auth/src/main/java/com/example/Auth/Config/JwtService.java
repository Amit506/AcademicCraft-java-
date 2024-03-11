package com.example.Auth.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class JwtService {
 private final String token;

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public JwtService(String token) {
        this.token = token;
    }

    public static String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private static String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private static Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim( Claims::getSubject);
    }

    public Date extractExpiration() {
        return extractClaim( Claims::getExpiration);
    }
    public Date extractIssuedAt() {
        return extractClaim( Claims::getIssuedAt);
    }

    public <T> T extractClaim(Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims();
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims() {
        Claims claims= Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims.toString());
        return claims;
    }
   public Map<String, Object> getClaimsMap() {
        Map<String, Object> claimsMap = new HashMap<>();

        for (Map.Entry<String, Object> entry : extractAllClaims().entrySet()) {
            claimsMap.put(entry.getKey(), entry.getValue());
        }
        return claimsMap;
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration().before(new Date());
    }

    public Boolean validateToken(String token) {
        final String username = extractUsername(token);
        return   !isTokenExpired(token);
    }


}

