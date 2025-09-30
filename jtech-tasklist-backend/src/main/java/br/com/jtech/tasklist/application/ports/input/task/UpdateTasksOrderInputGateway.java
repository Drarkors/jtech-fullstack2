package br.com.jtech.tasklist.application.ports.input.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskOrderDTO;

import java.util.List;
import java.util.Set;

/**
 * class UpdateTasksOrderInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface UpdateTasksOrderInputGateway {

  Set<Task> updateOrder(List<UpdateTaskOrderDTO> tasks, String userId);

}
