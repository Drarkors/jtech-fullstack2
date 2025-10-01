package br.com.jtech.tasklist.application.core.usecases.user.exceptions;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import org.springframework.http.HttpStatus;

public class InvalidUserCredentialsException extends ApiError {

  public InvalidUserCredentialsException() {
    super(HttpStatus.FORBIDDEN);
    this.setMessage("Invalid credentials");
  }

}
