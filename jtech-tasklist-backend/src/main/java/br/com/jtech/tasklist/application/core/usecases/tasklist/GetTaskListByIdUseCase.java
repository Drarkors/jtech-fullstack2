package br.com.jtech.tasklist.application.core.usecases.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListNotFoundException;
import br.com.jtech.tasklist.application.ports.input.tasklist.GetTaskListByIdInputGateway;
import br.com.jtech.tasklist.application.ports.output.tasklist.GetTaskListByIdOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetTaskListByIdUseCase implements GetTaskListByIdInputGateway {

  private final GetTaskListByIdOutputGateway outputGateway;

  public TaskList getById(String taskListId, String userId) {
    var optional = this.outputGateway.findById(taskListId);

    if (optional.isEmpty()) {
      throw new TaskListNotFoundException();
    }

    var entity = optional.get();

    if (!entity.getUserId().equals(userId)) {
      throw new UnauthorizedException();
    }

    var tasks = this.outputGateway.findAllTasksByTaskListId(entity.getId());
    entity.setTasks(tasks);

    return entity;
  }
}
