package br.com.jtech.tasklist.application.ports.input.tasklist.dtos;

public record UpdateTaskListInputDTO(String name, String description, Integer order) {
}
