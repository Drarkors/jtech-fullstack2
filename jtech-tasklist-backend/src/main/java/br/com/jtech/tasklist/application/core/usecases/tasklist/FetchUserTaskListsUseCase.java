package br.com.jtech.tasklist.application.core.usecases.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListUserNotFoundException;
import br.com.jtech.tasklist.application.ports.input.tasklist.FetchUserTaskListsInputGateway;
import br.com.jtech.tasklist.application.ports.output.tasklist.FetchUserTaskListsOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * class FetchTaskListsUseCase
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class FetchUserTaskListsUseCase implements FetchUserTaskListsInputGateway {

  private final FetchUserTaskListsOutputGateway outputGateway;

  @Transactional
  public Set<TaskList> fetchTaskLists(String userId) {
    var userExits = this.outputGateway.findUserById(userId)
      .isPresent();

    if (!userExits) {
      throw new TaskListUserNotFoundException();
    }

    return this.outputGateway.fetchUserTaskLists(userId);
  }
}
