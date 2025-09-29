package br.com.jtech.tasklist.application.ports.input.task;

import br.com.jtech.tasklist.application.core.entities.Task;

/**
 * class GetTaskByIdUseCase
 * <p>
 * user rafael.zanetti
 */
public interface GetTaskByIdInputGateway {

  Task getById(String id, String userId);

}
