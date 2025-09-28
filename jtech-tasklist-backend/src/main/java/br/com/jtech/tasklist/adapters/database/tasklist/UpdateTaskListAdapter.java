package br.com.jtech.tasklist.adapters.database.tasklist;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.ports.output.tasklist.UpdateTaskListUseCaseOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * class UpdateTaskListAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class UpdateTaskListAdapter implements UpdateTaskListUseCaseOutputGateway {

  private final TaskListRepository repository;

  @Override
  public Optional<TaskList> getTaskListById(String id) {
    var result = this.repository.findById(UUID.fromString(id));

    return result.map(TaskList::of);
  }

  @Override
  public TaskList update(TaskList taskList) {
    return TaskList.of(this.repository.save(taskList.toModel()));
  }

}
