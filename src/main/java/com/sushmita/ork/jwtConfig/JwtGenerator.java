package com.sushmita.ork.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.sushmita.ork.constants.SECURITYConstants.JWT_EXPIRATION;
import static com.sushmita.ork.constants.SECURITYConstants.JWT_SECRET;

/**
 * @author Sushmita Budhathoki on 2024-08-30
 */

@Component
public class JwtGenerator {

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate =  new Date(currentDate.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        //reaches in token and gets them
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            // Token is valid only if the expiration date is after the current date
            return !claims.getExpiration().before(new Date());
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature.");
        } catch (Exception e) {
            System.out.println("Invalid token.");
        }
        return false;
    }
}
