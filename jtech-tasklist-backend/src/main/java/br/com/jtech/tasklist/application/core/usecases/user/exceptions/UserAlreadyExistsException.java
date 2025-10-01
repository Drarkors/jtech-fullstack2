package br.com.jtech.tasklist.application.core.usecases.user.exceptions;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiError {

  public UserAlreadyExistsException() {
    super(HttpStatus.UNPROCESSABLE_ENTITY);
    this.setMessage("User already exists");
  }

}
