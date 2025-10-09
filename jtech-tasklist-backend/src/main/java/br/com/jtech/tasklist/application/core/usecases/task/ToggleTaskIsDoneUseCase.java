package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskNotFoundException;
import br.com.jtech.tasklist.application.ports.input.task.ToggleTaskIsDoneInputGateway;
import br.com.jtech.tasklist.application.ports.output.task.ToggleTaskIsDoneOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * class ToggleTaskIsDoneUseCase
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class ToggleTaskIsDoneUseCase implements ToggleTaskIsDoneInputGateway {

  private final ToggleTaskIsDoneOutputGateway outputGateway;

  @Transactional
  public Task toggleIsDone(String id, String userId) {
    var optional = this.outputGateway.findById(id);

    if (optional.isEmpty()) {
      throw new TaskNotFoundException();
    }

    var entity = optional.get();

    if (!entity.getTaskList().getUserId().equals(userId)) {
      throw new UnauthorizedException();
    }

    entity.setIsDone(!entity.getIsDone());

    return this.outputGateway.update(entity);
  }
}
