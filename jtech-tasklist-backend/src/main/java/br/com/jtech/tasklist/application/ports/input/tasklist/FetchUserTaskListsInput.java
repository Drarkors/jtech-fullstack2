package br.com.jtech.tasklist.application.ports.input.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Set;
import java.util.UUID;

/**
 * class FetchUserTaskListInput
 * <p>
 * user rafael.zanetti
 */
public interface FetchUserTaskListsInput {

  Set<TaskList> fetchUserTaskLists(UUID userId);

}
