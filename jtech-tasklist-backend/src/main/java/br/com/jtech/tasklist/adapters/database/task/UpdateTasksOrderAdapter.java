package br.com.jtech.tasklist.adapters.database.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.ports.output.task.UpdateTasksOrderOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * class UpdateTasksOrderAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class UpdateTasksOrderAdapter implements UpdateTasksOrderOutputGateway {

  private final TaskRepository repository;

  @Override
  public Map<String, Task> findAllByIds(String[] ids) {
    return this.repository.findAllById(Arrays.stream(ids).map(UUID::fromString).toList())
      .stream().map(Task::of).collect(Collectors.toMap(Task::getId, task -> task));
  }

  @Override
  public Set<Task> updateAll(Set<Task> tasks) {
    return this.repository.saveAll(tasks.stream().map(Task::toModel).toList())
      .stream().map(Task::of).collect(Collectors.toSet());
  }

}
