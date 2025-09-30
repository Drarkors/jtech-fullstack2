package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TasksNotFoundException;
import br.com.jtech.tasklist.application.ports.input.task.UpdateTasksOrderInputGateway;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskOrderDTO;
import br.com.jtech.tasklist.application.ports.output.task.UpdateTasksOrderOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * class UpdateTasksOrderUseCase
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class UpdateTasksOrderUseCase implements UpdateTasksOrderInputGateway {

  private final UpdateTasksOrderOutputGateway outputGateway;

  @Override
  public Set<Task> updateOrder(List<UpdateTaskOrderDTO> tasksToUpdate, String userId) {
    Set<String> ids = tasksToUpdate.stream()
      .map(UpdateTaskOrderDTO::id)
      .collect(Collectors.toSet());

    Map<String, Task> entities = this.outputGateway.findAllByIds(ids.toArray(String[]::new));

    if (ids.size() != entities.size()) {
      throw new TasksNotFoundException();
    }

    for (Task entity : entities.values()) {
      if (!entity.getTaskList().getUserId().equals(userId)) {
        throw new UnauthorizedException();
      }
    }

    for (UpdateTaskOrderDTO taskUpdate : tasksToUpdate) {
      Task task = entities.get(taskUpdate.id());
      task.setOrder(taskUpdate.newOrder());
    }

    return this.outputGateway.updateAll(new HashSet<>(entities.values()));
  }
}
