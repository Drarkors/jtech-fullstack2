package br.com.jtech.tasklist.application.ports.output.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Optional;

public interface UpdateTaskListOutputGateway {

  Optional<TaskList> findById(String id);

  TaskList update(TaskList taskList);

}
