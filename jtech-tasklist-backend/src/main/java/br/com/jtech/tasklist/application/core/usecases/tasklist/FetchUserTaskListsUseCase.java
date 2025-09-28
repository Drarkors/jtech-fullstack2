package br.com.jtech.tasklist.application.core.usecases.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListUserNotFoundException;
import br.com.jtech.tasklist.application.ports.input.tasklist.FetchUserTaskListsInputGateway;
import br.com.jtech.tasklist.application.ports.output.tasklist.FetchUserTaskListsOutputGateway;

import java.util.Set;
import java.util.UUID;

/**
 * class FetchTaskListsUseCase
 * <p>
 * user rafael.zanetti
 */
public class FetchUserTaskListsUseCase implements FetchUserTaskListsInputGateway {

  private final FetchUserTaskListsOutputGateway outputGateway;

  public FetchUserTaskListsUseCase(FetchUserTaskListsOutputGateway outputGateway) {
    this.outputGateway = outputGateway;
  }

  public Set<TaskList> fetchTaskLists(UUID userId) {
    var userExits = this.outputGateway.findUserById(userId)
      .isPresent();

    if (!userExits) {
      throw new TaskListUserNotFoundException();
    }

    return this.outputGateway.fetchUserTaskLists(userId);
  }
}
