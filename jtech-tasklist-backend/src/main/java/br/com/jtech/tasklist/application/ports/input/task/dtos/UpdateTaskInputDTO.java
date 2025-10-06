package br.com.jtech.tasklist.application.ports.input.task.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTaskInputDTO(
  @NotNull(message = "Field \"name\" is required")
  @NotEmpty(message = "Field \"name\" is required")
  @Size(min = 4, max = 100, message = "Field \"name\" must have from 4 up to 100 characters")
  String name,
  @Size(max = 500, message = "Field \"description\" can have up to 500 characters")
  String description) {
}
