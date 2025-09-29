package br.com.jtech.tasklist.adapters.database.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.ports.output.task.UpdateTaskOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateTaskAdapter implements UpdateTaskOutputGateway {

  private final TaskRepository repository;

  @Override
  public Optional<Task> findById(String id) {
    var entity = this.repository.findById(UUID.fromString(id));

    return entity.map(Task::of);
  }

  @Override
  public Task update(Task task) {
    return Task.of(this.repository.save(task.toModel()));
  }
  
}
