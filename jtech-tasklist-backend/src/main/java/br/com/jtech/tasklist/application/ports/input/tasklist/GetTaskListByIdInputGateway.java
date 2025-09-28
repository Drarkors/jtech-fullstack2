package br.com.jtech.tasklist.application.ports.input.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.UUID;

/**
 * class GetUserByIdInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface GetTaskListByIdInputGateway {

  TaskList getById(UUID taskListId, UUID userId);

}
