package br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import org.springframework.http.HttpStatus;

public class TaskListNotFoundException extends ApiError {

  public TaskListNotFoundException() {
    super(HttpStatus.NOT_FOUND);
    this.setMessage("Task list not found");
  }

}
