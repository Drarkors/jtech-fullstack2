package br.com.jtech.tasklist.application.ports.input.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

/**
 * class GetUserByIdInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface GetTaskListByIdInputGateway {

  TaskList getById(String taskListId, String userId);

}
