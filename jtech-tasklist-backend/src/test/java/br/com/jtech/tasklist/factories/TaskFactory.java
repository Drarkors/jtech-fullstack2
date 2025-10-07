package br.com.jtech.tasklist.factories;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.UUID;

public class TaskFactory {

  private final TaskRepository taskRepository;
  private final TaskListRepository taskListRepository;

  public TaskFactory(TaskRepository taskRepository, TaskListRepository taskListRepository) {
    this.taskRepository = taskRepository;
    this.taskListRepository = taskListRepository;
  }

  public Task makeTask(String taskListId) {
    if (taskListId == null) {
      throw new RuntimeException("Can't build a task without a taskListId");
    }

    var taskListOptional = this.taskListRepository.findById(UUID.fromString(taskListId));

    if (taskListOptional.isEmpty())
      throw new RuntimeException("Can't build a task without a task list");

    var entity = Task.builder()
      .name("Task")
      .description("Description")
      .order(0)
      .taskListId(taskListId)
      .taskList(TaskList.of(taskListOptional.get()))
      .build();

    entity = Task.of(this.taskRepository.save(entity.toModel()));

    return entity;
  }

  public Task makeTask(String taskListId, Integer order) {
    if (taskListId == null) {
      throw new RuntimeException("Can't build a task without a taskListId");
    }

    var taskListOptional = this.taskListRepository.findById(UUID.fromString(taskListId));

    if (taskListOptional.isEmpty())
      throw new RuntimeException("Can't build a task without a task list");

    var entity = Task.builder()
      .name("Task " + order.toString())
      .description("Description")
      .order(order)
      .taskListId(taskListId)
      .taskList(TaskList.of(taskListOptional.get()))
      .build();

    entity = Task.of(this.taskRepository.save(entity.toModel()));

    return entity;
  }

}
