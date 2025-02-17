package com.Lokenra.calmly_speak.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    private static final String SECRET = "e999c5078cb7388d2779d214fa4a1e1eaf2329fd384c99fc978d2f8a756c1a92";
    public static final SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));

    public String generateToken(UserDetails userDetails) {
        HashMap<String, String> claims = new HashMap<>();
        claims.put("Author", "Lokenra Dhakrey");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(1000)))
                .signWith(secretKey)
                .compact();

    }

    public String extractUsername(String jwtToken) {
        Claims claims = getClaims(jwtToken);
        return claims.getSubject();
    }

    public Claims getClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public boolean validateToken(String jwtToken) {
        Claims claims = getClaims(jwtToken);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
