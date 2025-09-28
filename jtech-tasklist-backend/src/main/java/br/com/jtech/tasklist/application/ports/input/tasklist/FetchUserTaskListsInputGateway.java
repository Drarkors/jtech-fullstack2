package br.com.jtech.tasklist.application.ports.input.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Set;

/**
 * class FetchUserTaskListInput
 * <p>
 * user rafael.zanetti
 */
public interface FetchUserTaskListsInputGateway {

  Set<TaskList> fetchTaskLists(String userId);

}
