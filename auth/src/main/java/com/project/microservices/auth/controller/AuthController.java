package com.project.microservices.auth.controller;

import com.project.microservices.auth.dto.TokenDTO;
import com.project.microservices.auth.dto.UserDTO;
import com.project.microservices.auth.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @RequestMapping("/testeSecurity")
  public String teste() {
    return "testado";
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public TokenDTO login(@RequestBody UserDTO userDTO) {
    String token = authService.login(userDTO.getUsername(), userDTO.getPassword());
    return TokenDTO.builder().username(userDTO.getUsername()).token(token).build();
  }
}