package com.inverse.project.Jobless.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "462D4A614E645267556B58703273357638792F423F4528472B4B625065536856"; // 462D4A614E645267556B58703273357638792F423F4528472B4B625065536856

    public String extractUsername(String TOKEN) {
        return extractClaim(TOKEN, Claims::getSubject);
    }

    public <T> T extractClaim(String TOKEN, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(TOKEN);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Objects> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String TOKEN, UserDetails userDetails){
        final String username = extractUsername(TOKEN);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(TOKEN);
    }

    private boolean isTokenExpired(String TOKEN) {
        return extractExpiration(TOKEN).before(new Date());
    }

    private Date extractExpiration(String TOKEN) {
        return extractClaim(TOKEN, Claims::getExpiration);
    }

    private Claims extractAllClaims(String TOKEN){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(TOKEN)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
