package br.com.jtech.tasklist.adapters.database.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.ports.output.task.CreateTaskOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * class CreateTaskAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class CreateTaskAdapter implements CreateTaskOutputGateway {

  private final TaskRepository repository;
  private final TaskListRepository listRepository;

  @Override
  public Task create(Task task) {
    var entity = this.repository.save(task.toModel());

    return Task.of(entity);
  }

  @Override
  public Optional<Task> findByNameAndTaskList(String name, String taskListId) {
    var entity = this.repository.findByNameAndTaskListId(name, UUID.fromString(taskListId));

    return entity.map(Task::of);
  }

  @Override
  public Optional<TaskList> getTaskListFromTask(String taskListId) {
    var entity = this.listRepository.findById(UUID.fromString(taskListId));

    return entity.map(TaskList::of);
  }


}
