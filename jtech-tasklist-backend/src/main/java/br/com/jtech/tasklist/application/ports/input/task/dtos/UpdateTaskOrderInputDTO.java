package br.com.jtech.tasklist.application.ports.input.task.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record UpdateTaskOrderInputDTO(
  @NotNull(message = "Field \"name\" is required")
  @NotEmpty(message = "Field \"name\" is required")
  String id,
  @NotNull(message = "Field \"name\" is required")
  @Min(value = 0, message = "Field \"order\" cannot be less than 0")
  Integer newOrder) {
  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof UpdateTaskOrderInputDTO that)) return false;
    return Objects.equals(id, that.id) && Objects.equals(newOrder, that.newOrder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, newOrder);
  }
}
