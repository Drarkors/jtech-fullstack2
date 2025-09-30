package br.com.jtech.tasklist.application.ports.input.task.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskOrderDTO(
  @NotNull(message = "Field \"name\" is required")
  @NotEmpty(message = "Field \"name\" is required")
  String id,
  @NotNull(message = "Field \"name\" is required")
  @Min(value = 0, message = "Field \"order\" cannot be less than 0")
  Integer newOrder) {
}
