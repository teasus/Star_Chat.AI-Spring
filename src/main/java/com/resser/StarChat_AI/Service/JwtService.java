package com.resser.StarChat_AI.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String key ;



    private SecretKey generateSigningKey () {
        System.out.println("Secret "+Decoders.BASE64.decode(key));
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public String generateToken(String username) {

        return Jwts.builder().subject(username).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+(6000*1000)))
                .signWith(generateSigningKey()).compact();

    }


    public Claims extractClaims(String token){
        Claims claim = Jwts.parser().verifyWith(generateSigningKey()).build().parseSignedClaims(token).getPayload();
        return claim;
    }

    public boolean isTokenValid(String token){




        return new Date().before(extractExpirationDate(token));
    }
    public String extractSubject(String token){
        return extractClaims(token).getSubject();
    }

    public Date extractExpirationDate(String token){
        return extractClaims(token).getExpiration();
    }




}
