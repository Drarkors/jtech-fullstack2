package br.com.jtech.tasklist.application.ports.output.task;

import br.com.jtech.tasklist.application.core.entities.Task;

import java.util.Optional;

public interface UpdateTaskOutputGateway {

  Optional<Task> findById(String id);

  Task update(Task task);

}
