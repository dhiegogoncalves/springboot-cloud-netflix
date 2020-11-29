package com.project.microservices.pagamento.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {

  private String fields;
  private String fieldsMessage;

}