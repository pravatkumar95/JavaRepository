package com.pks.service;

import com.pks.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component

public class JwtUtil {
    @Value("${token.expiration.time}")
    private Long expiration;
    private  JwtConfig jwtConfig;
    public  JwtUtil(JwtConfig jwtConfig){
        this.jwtConfig=jwtConfig;
    }


    public String generateToken(String username){


        LocalDateTime now= LocalDateTime.now();
        LocalDateTime expiry=now.plusMinutes(expiration);
        Date issuedAt=Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date expiration=Date.from(expiry.atZone(ZoneId.systemDefault()).toInstant());


        return Jwts.builder().setAudience("centroxy")
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256,jwtConfig.getSecretKey())
                .compact();

     }

     public Claims extractClaims(String token){
       return  Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getSecretKey()).build().parseClaimsJws(token).getBody();


     }
     public String extractUserName(String token){

        return extractClaims(token).getSubject();


     }

     public LocalDateTime extractIssuedAt(String token){

        Date issueTime=extractClaims(token).getIssuedAt();
        return issueTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
     }
     public LocalDateTime extractExpiration(String token){

        Date expirationTime=extractClaims(token).getExpiration();
        return expirationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

     }

     public boolean validateToken(String token){
        return extractExpiration(token).isAfter(LocalDateTime.now());
     }


}
