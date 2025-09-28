package br.com.jtech.tasklist.application.ports.input.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;

public interface UpdateTaskListInputGateway {

  TaskList update(String id, String userId, String name, Integer order);

}
