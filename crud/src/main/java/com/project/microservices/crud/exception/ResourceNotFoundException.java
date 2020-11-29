package com.project.microservices.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 3295802460927613715L;

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
