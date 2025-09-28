package br.com.jtech.tasklist.application.ports.output.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Optional;

/**
 * class CreateTaskOutputGateway
 * <p>
 * user rafael.zanetti
 */
public interface CreateTaskOutputGateway {

  Task create(Task task);

  Optional<Task> findByNameAndTaskList(String name, String taskListId);

  Optional<TaskList> getTaskListFromTask(String taskListId);

}
