package br.com.jtech.tasklist.adapters.database.tasklist;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.ports.output.tasklist.GetTaskListByIdOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * class GetTaskListByIdAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class GetTaskListByIdAdapter implements GetTaskListByIdOutputGateway {

  private final TaskListRepository repository;

  @Override
  public Optional<TaskList> getTaskListById(UUID id) {
    var result = this.repository.findById(id);

    return result.map(TaskList::of);
  }
}
