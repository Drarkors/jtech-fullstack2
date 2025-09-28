package br.com.jtech.tasklist.config.infra.exceptions.shared;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiError {

  public UnauthorizedException() {
    super(HttpStatus.UNAUTHORIZED);
  }

}
