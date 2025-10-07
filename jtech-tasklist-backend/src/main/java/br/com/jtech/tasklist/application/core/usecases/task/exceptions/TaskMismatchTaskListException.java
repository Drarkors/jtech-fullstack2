package br.com.jtech.tasklist.application.core.usecases.task.exceptions;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import org.springframework.http.HttpStatus;

public class TaskMismatchTaskListException extends ApiError {

  public TaskMismatchTaskListException() {
    super(HttpStatus.NOT_FOUND);
    this.setMessage("A task doesn't belong to this task list");
  }

}