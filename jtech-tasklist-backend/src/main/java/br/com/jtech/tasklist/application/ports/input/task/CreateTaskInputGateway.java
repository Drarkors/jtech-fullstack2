package br.com.jtech.tasklist.application.ports.input.task;

import br.com.jtech.tasklist.application.core.entities.Task;

/**
 * class CreateTaskInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface CreateTaskInputGateway {

  Task create(Task task, String userId);

}
