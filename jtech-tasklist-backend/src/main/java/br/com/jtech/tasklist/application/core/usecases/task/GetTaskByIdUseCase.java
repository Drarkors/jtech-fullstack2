package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskNotFoundException;
import br.com.jtech.tasklist.application.ports.input.task.GetTaskByIdInputGateway;
import br.com.jtech.tasklist.application.ports.output.task.GetTaskByIdOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetTaskByIdUseCase implements GetTaskByIdInputGateway {

  private final GetTaskByIdOutputGateway outputGateway;

  public Task getById(String id, String userId) {
    var optional = this.outputGateway.findById(id);

    if (optional.isEmpty()) {
      throw new TaskNotFoundException();
    }

    var entity = optional.get();

    if (!entity.getTaskList().getUserId().equals(userId)) {
      throw new UnauthorizedException();
    }

    return entity;
  }

}
