package br.com.jtech.tasklist.application.ports.input.tasklist.dtos;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Set;

public record GetTaskListByIdInputDTO(TaskList taskList, Set<Task> tasks) {
}
