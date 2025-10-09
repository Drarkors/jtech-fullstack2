package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskMismatchTaskListException;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TasksNotFoundException;
import br.com.jtech.tasklist.application.ports.input.task.UpdateTasksOrderInputGateway;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskOrderInputDTO;
import br.com.jtech.tasklist.application.ports.output.task.UpdateTasksOrderOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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

  @Transactional
  public Set<Task> updateOrder(Set<UpdateTaskOrderInputDTO> tasksToUpdate, String taskListId, String userId) {
    Set<String> ids = tasksToUpdate.stream()
      .map(UpdateTaskOrderInputDTO::id)
      .collect(Collectors.toSet());

    Map<String, Task> entities = this.outputGateway.findAllByIds(ids.toArray(String[]::new));

    if (ids.size() != entities.size()) {
      throw new TasksNotFoundException();
    }

    var mismatchedTaskListCount = entities.values().stream()
      .filter(task -> !task.getTaskListId().equals(taskListId)).count();

    if (mismatchedTaskListCount > 0) {
      throw new TaskMismatchTaskListException();
    }

    for (Task entity : entities.values()) {
      if (!entity.getTaskList().getUserId().equals(userId)) {
        throw new UnauthorizedException();
      }
    }

    for (UpdateTaskOrderInputDTO taskUpdate : tasksToUpdate) {
      Task task = entities.get(taskUpdate.id());
      task.setOrder(taskUpdate.newOrder());
    }

    return this.outputGateway.updateAll(new HashSet<>(entities.values()));
  }
}
