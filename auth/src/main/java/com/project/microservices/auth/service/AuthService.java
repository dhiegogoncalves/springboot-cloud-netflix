package com.project.microservices.auth.service;

import com.project.microservices.auth.jwt.JwtTokenProvider;
import com.project.microservices.auth.model.User;
import com.project.microservices.auth.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  public String login(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

      User user = userRepository.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("Username: '" + username + "' not found"));

      return jwtTokenProvider.createToken(username, user.getRoles());
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username/password");
    }
  }
}
