package br.com.jtech.tasklist.factories;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.config.infra.utils.GenId;

public class TaskListFactory {

  private final TaskListRepository repository;

  public TaskListFactory(TaskListRepository repository) {
    this.repository = repository;
  }

  public static TaskList fakeTaskListWithoutId() {
    return TaskList.builder()
      .name("List")
      .description("Description")
      .order(0)
      .build();
  }

  public static TaskList fakeTaskList(String userId) {
    return TaskList.builder()
      .id(GenId.newId())
      .userId(userId)
      .name("List")
      .description("Description")
      .order(0)
      .build();
  }

  public TaskList makeTaskList(User user) {
    var entity = TaskList.builder()
      .name("Task list")
      .description("Description")
      .order(0)
      .userId(user.getId())
      .build();

    return TaskList.of(this.repository.save(entity.toModel()));
  }

}
