package br.com.jtech.tasklist.application.ports.input;

import br.com.jtech.tasklist.application.core.entities.Task;

/**
 * class CreateTaskInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface CreateTaskInputGateway {

  Task create(Task task);

}
