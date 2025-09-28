package br.com.jtech.tasklist.application.ports.output.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Optional;

public interface GetTaskListByIdOutputGateway {

  Optional<TaskList> getTaskListById(String id);

}
