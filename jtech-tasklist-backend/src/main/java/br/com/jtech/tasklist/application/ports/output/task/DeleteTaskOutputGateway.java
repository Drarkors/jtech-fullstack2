package br.com.jtech.tasklist.application.ports.output.task;

import br.com.jtech.tasklist.application.core.entities.Task;

import java.util.Optional;

/**
 * class DeleteTaskOutputGateway
 * <p>
 * user rafael.zanetti
 */
public interface DeleteTaskOutputGateway {
  Optional<Task> findById(String id);

  void delete(String id);
}
