package br.com.jtech.tasklist.application.core.usecases.task.exceptions;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import org.springframework.http.HttpStatus;

public class TasksNotFoundException extends ApiError {

  public TasksNotFoundException() {
    super(HttpStatus.UNPROCESSABLE_ENTITY);
    this.setMessage("One or more tasks not found");
  }

}