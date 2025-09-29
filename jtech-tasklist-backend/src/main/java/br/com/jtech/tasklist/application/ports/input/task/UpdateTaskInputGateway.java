package br.com.jtech.tasklist.application.ports.input.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskInputDTO;

/**
 * class GetTaskByIdUseCase
 * <p>
 * user rafael.zanetti
 */
public interface UpdateTaskInputGateway {

  Task update(UpdateTaskInputDTO dto, String id, String userId);

}
