package br.com.jtech.tasklist.adapters.database.tasklist;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.ports.output.tasklist.DeleteTaskListUseCaseOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * class DeleteTaskListAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class DeleteTaskListAdapter implements DeleteTaskListUseCaseOutputGateway {

  private final TaskListRepository repository;

  @Override
  public Optional<TaskList> findById(String id) {
    var result = this.repository.findById(UUID.fromString(id));

    return result.map(TaskList::of);
  }

  @Override
  public void delete(String id) {
    this.repository.deleteById(UUID.fromString(id));
  }
}
