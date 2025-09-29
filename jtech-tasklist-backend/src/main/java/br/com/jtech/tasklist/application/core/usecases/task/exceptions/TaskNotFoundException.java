package br.com.jtech.tasklist.application.core.usecases.task.exceptions;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import org.springframework.http.HttpStatus;

public class TaskNotFoundException extends ApiError {

  public TaskNotFoundException() {
    super(HttpStatus.NOT_FOUND);
    this.setMessage("Task not found");
  }

}
