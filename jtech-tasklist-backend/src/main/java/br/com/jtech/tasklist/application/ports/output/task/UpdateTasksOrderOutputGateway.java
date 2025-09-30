package br.com.jtech.tasklist.application.ports.output.task;

import br.com.jtech.tasklist.application.core.entities.Task;

import java.util.Map;
import java.util.Set;

/**
 * class UpdateTasksOrderOutputGateway
 * <p>
 * user rafael.zanetti
 */
public interface UpdateTasksOrderOutputGateway {
  Map<String, Task> findAllByIds(String[] ids);

  Set<Task> updateAll(Set<Task> task);

}
