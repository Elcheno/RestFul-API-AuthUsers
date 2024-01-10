package com.example.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    // Generar token
    public String generateTokenAccess(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSeignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validar token
    public boolean validateTokenAccess(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSeignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (Exception e) {
            log.error("Token invalido, error: ".concat(e.getMessage()));
            return false;

        }
    }

    // Obtener todos los claims del token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSeignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtener username del token
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Obtener un solo Claim del token
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    // Obtener firma del token
    public Key getSeignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
