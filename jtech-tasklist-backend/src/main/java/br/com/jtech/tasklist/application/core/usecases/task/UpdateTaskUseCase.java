package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskNotFoundException;
import br.com.jtech.tasklist.application.ports.input.task.UpdateTaskInputGateway;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskInputDTO;
import br.com.jtech.tasklist.application.ports.output.task.UpdateTaskOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * class UpdateTaskUseCase
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class UpdateTaskUseCase implements UpdateTaskInputGateway {

  private final UpdateTaskOutputGateway outputGateway;

  @Transactional
  public Task update(UpdateTaskInputDTO dto, String id, String userId) {
    var optional = this.outputGateway.findById(id);

    if (optional.isEmpty()) {
      throw new TaskNotFoundException();
    }

    var entity = optional.get();

    if (!entity.getTaskList().getUserId().equals(userId)) {
      throw new UnauthorizedException();
    }

    entity.setName(dto.name());
    entity.setDescription(dto.description());

    return this.outputGateway.update(entity);
  }
}
