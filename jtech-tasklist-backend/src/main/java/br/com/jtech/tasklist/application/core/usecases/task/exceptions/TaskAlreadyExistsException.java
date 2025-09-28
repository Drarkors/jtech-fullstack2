package br.com.jtech.tasklist.application.core.usecases.task.exceptions;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import org.springframework.http.HttpStatus;

public class TaskAlreadyExistsException extends ApiError {

  public TaskAlreadyExistsException() {
    super(HttpStatus.UNPROCESSABLE_ENTITY);
    this.setMessage("A task with given name and list already exists");
  }
  
}
