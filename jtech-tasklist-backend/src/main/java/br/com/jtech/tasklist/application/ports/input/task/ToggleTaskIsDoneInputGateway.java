package br.com.jtech.tasklist.application.ports.input.task;

import br.com.jtech.tasklist.application.core.entities.Task;

/**
 * class ToggleTaskIsDoneInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface ToggleTaskIsDoneInputGateway {

  Task markAsDone(String id, String userId);

}
