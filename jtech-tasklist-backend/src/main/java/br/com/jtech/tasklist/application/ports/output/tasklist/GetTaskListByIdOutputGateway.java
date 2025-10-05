package br.com.jtech.tasklist.application.ports.output.tasklist;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Optional;
import java.util.Set;

public interface GetTaskListByIdOutputGateway {

  Optional<TaskList> findById(String id);

  Set<Task> findAllTasksByTaskListId(String taskListId);

}
