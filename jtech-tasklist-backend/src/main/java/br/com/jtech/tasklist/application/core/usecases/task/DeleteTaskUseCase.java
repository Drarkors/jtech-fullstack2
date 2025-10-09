package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskNotFoundException;
import br.com.jtech.tasklist.application.ports.input.task.DeleteTaskInputGateway;
import br.com.jtech.tasklist.application.ports.output.task.DeleteTaskOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import lombok.RequiredArgsConstructor;

/**
 * class DeleteTaskUseCase
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class DeleteTaskUseCase implements DeleteTaskInputGateway {

  private final DeleteTaskOutputGateway outputGateway;

  public void delete(String id, String userId) {
    var optional = this.outputGateway.findById(id);

    if (optional.isEmpty()) {
      throw new TaskNotFoundException();
    }

    var entity = optional.get();

    if (!entity.getTaskList().getUserId().equals(userId)) {
      throw new UnauthorizedException();
    }

    this.outputGateway.delete(entity.getId());
  }

}
