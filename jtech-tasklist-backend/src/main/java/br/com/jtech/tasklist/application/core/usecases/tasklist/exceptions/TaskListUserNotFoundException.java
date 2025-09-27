package br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import org.springframework.http.HttpStatus;

public class TaskListUserNotFoundException extends ApiError {

  public TaskListUserNotFoundException() {
    super(HttpStatus.UNPROCESSABLE_ENTITY);
    this.setMessage("No user was found with given userId");
  }

}
