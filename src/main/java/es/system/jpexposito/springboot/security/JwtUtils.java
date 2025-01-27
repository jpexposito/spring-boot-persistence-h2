package es.system.jpexposito.springboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); 

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; 
    private static final String JWT_PREFIX = "Bearer ";


    public List<String> getRoleNames(Collection<? extends GrantedAuthority> authorities) {
            return authorities.stream()
                            .map(GrantedAuthority::getAuthority)  
                            .collect(Collectors.toList());      
        }

    public String generateToken(String username,Collection<? extends GrantedAuthority> autorities) {
        
        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", getRoleNames(autorities))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY) 
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token.replace(JWT_PREFIX, ""))
                .getBody()
                .getSubject();
    }

    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(SECRET_KEY)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        return claims.get("authorities", List.class);  
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token.replace(JWT_PREFIX, ""));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
