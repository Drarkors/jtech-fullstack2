package br.com.jtech.tasklist.application.ports.output.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Optional;

public interface UpdateTaskListUseCaseOutputGateway {

  Optional<TaskList> findById(String id);

  TaskList update(TaskList taskList);

}
