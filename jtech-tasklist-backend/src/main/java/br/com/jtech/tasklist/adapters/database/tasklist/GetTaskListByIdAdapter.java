package br.com.jtech.tasklist.adapters.database.tasklist;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.ports.output.tasklist.GetTaskListByIdOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * class GetTaskListByIdAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class GetTaskListByIdAdapter implements GetTaskListByIdOutputGateway {

  private final TaskListRepository repository;
  private final TaskRepository taskRepository;

  @Override
  public Optional<TaskList> findById(String id) {
    var result = this.repository.findById(UUID.fromString(id));

    return result.map(TaskList::of);
  }

  @Override
  public Set<Task> findAllTasksByTaskListId(String taskListId) {
    var result = this.taskRepository.findAllByTaskListId(UUID.fromString(taskListId));

    if (result.isEmpty()) {
      return Collections.emptySet();
    }

    return result.stream().map(Task::of).collect(Collectors.toSet());
  }

}
