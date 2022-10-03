package com.example.onliner.security;

import com.example.onliner.domain.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private static final String ROLE = "role";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private long validityMs = 120000;
    private String secret = "secret";

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtUtil(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username, List<User.UserRole> roles){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(ROLE, roles);
        Date dateNow = new Date();
        Date expiration = new Date(dateNow.getTime() + validityMs);
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(dateNow)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try{
            Jws<Claims> claimsJwt = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claimsJwt.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomAuthenticationException("Invalid token " + token, e);
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if(bearerToken != null && bearerToken.startsWith(BEARER)){
            return bearerToken.substring(7);
        }
        return null;
    }

}
