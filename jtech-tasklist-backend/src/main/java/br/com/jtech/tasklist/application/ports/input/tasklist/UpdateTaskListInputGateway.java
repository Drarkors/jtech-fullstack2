package br.com.jtech.tasklist.application.ports.input.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.ports.input.tasklist.dtos.UpdateTaskListInputDTO;

public interface UpdateTaskListInputGateway {

  TaskList update(UpdateTaskListInputDTO dto, String id, String userId);

}
