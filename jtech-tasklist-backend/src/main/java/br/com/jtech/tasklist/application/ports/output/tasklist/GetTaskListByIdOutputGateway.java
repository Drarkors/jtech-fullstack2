package br.com.jtech.tasklist.application.ports.output.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

import java.util.Optional;
import java.util.UUID;

public interface GetTaskListByIdOutputGateway {

  Optional<TaskList> getTaskListById(UUID id);

}
