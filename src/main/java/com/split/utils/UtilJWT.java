package com.split.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UtilJWT{

    private final String SECRET_KEY = "secretkeykeysecretkeysecretkeysecretkeysecret";


    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUserId(String token) {
        return extractAllClaims(token).get("userId").toString();
    }
    private Claims extractAllClaims(String token) {
        String jwtTokenWithoutBearer = token.substring(7);
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtTokenWithoutBearer).getBody();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            // Token hatalı veya süresi dolmuş
            return false;
        }
    }
}

