package br.com.jtech.tasklist.application.core.usecases.tasklist;

import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListNotFoundException;
import br.com.jtech.tasklist.application.ports.input.tasklist.DeleteTaskListInputGateway;
import br.com.jtech.tasklist.application.ports.output.tasklist.DeleteTaskListOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import lombok.RequiredArgsConstructor;

/**
 * class DeleteTaskListUseCase
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class DeleteTaskListUseCase implements DeleteTaskListInputGateway {

  private final DeleteTaskListOutputGateway outputGateway;


  @Override
  public void delete(String id, String userId) {
    var output = outputGateway.findById(id);

    if (output.isEmpty()) {
      throw new TaskListNotFoundException();
    }

    var entity = output.get();

    if (!entity.getUserId().equals(userId)) {
      throw new UnauthorizedException();
    }

    this.outputGateway.delete(entity.getId());
  }

}
