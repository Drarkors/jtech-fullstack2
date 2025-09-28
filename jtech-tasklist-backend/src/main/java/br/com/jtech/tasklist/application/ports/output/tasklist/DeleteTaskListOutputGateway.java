package br.com.jtech.tasklist.application.ports.output.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Optional;

public interface DeleteTaskListOutputGateway {

  Optional<TaskList> findById(String id);

  void delete(String id);

}
