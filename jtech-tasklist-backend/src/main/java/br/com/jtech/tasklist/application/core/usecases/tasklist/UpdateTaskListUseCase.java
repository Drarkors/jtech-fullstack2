package br.com.jtech.tasklist.application.core.usecases.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListNotFoundException;
import br.com.jtech.tasklist.application.ports.input.tasklist.UpdateTaskListInputGateway;
import br.com.jtech.tasklist.application.ports.output.tasklist.UpdateTaskListOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateTaskListUseCase implements UpdateTaskListInputGateway {

  private final UpdateTaskListOutputGateway outputGateway;

  public TaskList update(String id, String userId, String name, Integer order) {
    var output = this.outputGateway.findById(id);

    if (output.isEmpty()) {
      throw new TaskListNotFoundException();
    }

    var entity = output.get();

    if (!entity.getUserId().equals(userId)) {
      throw new UnauthorizedException();
    }

    entity.setName(name);
    entity.setOrder(order);

    return this.outputGateway.update(entity);
  }

}
