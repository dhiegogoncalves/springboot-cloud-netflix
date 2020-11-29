package com.project.microservices.auth.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.project.microservices.auth.service.UserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

  @Value("${security.jwt.token.secret-key}")
  private String secretKey;

  @Value("${security.jwt.token.time-expire}")
  private Long timeExpire;

  private final UserService userService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String username, List<String> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", roles);

    Date issuedAt = new Date();
    Date expiration = new Date(issuedAt.getTime() + timeExpire);

    return Jwts.builder().setClaims(claims).setIssuedAt(issuedAt).setExpiration(expiration)
        .signWith(SignatureAlgorithm.HS256, secretKey).compact();
  }

  public Authentication getAuthentication(String token) {
    String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    UserDetails userDetails = userService.loadUserByUsername(username);
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      if (claims.getBody().getExpiration().before(new Date())) {
        return false;
      }
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

}
