package br.com.jtech.tasklist.application.ports.input.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskOrderInputDTO;

import java.util.Set;

/**
 * class UpdateTasksOrderInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface UpdateTasksOrderInputGateway {

  Set<Task> updateOrder(Set<UpdateTaskOrderInputDTO> tasks, String taskListId, String userId);

}
