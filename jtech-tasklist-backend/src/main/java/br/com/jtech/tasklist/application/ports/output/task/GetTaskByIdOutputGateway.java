package br.com.jtech.tasklist.application.ports.output.task;

import br.com.jtech.tasklist.application.core.entities.Task;

import java.util.Optional;

/**
 * class GetTaskByIdOutputGateway
 * <p>
 * user rafael.zanetti
 */
public interface GetTaskByIdOutputGateway {

  Optional<Task> findById(String id);

}
