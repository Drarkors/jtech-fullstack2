package br.com.jtech.tasklist.adapters.database.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.ports.output.task.DeleteTaskOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * class DeleteTaskAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class DeleteTaskAdapter implements DeleteTaskOutputGateway {

  private final TaskRepository repository;

  @Override
  public Optional<Task> findById(String id) {
    var entity = this.repository.findById(UUID.fromString(id));

    return entity.map(Task::of);
  }

  @Override
  public void delete(String id) {
    this.repository.deleteById(UUID.fromString(id));
  }

}
