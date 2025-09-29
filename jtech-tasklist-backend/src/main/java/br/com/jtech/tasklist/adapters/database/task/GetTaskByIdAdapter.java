package br.com.jtech.tasklist.adapters.database.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.ports.output.task.GetTaskByIdOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * class GetTaskByIdAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class GetTaskByIdAdapter implements GetTaskByIdOutputGateway {

  private final TaskRepository repository;

  @Override
  public Optional<Task> findById(String id) {
    var entity = this.repository.findById(UUID.fromString(id));

    return entity.map(Task::of);
  }

}
