package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskAlreadyExistsException;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListNotFoundException;
import br.com.jtech.tasklist.application.ports.input.task.CreateTaskInputGateway;
import br.com.jtech.tasklist.application.ports.output.task.CreateTaskOutputGateway;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;

/**
 * class CreateTaskUseCase
 * <p>
 * user rafael.zanetti
 */
public class CreateTaskUseCase implements CreateTaskInputGateway {

  private final CreateTaskOutputGateway outputGateway;

  public CreateTaskUseCase(CreateTaskOutputGateway outputGateway) {
    this.outputGateway = outputGateway;
  }

  public Task create(Task task, String userId) {
    var taskListOptional = this.outputGateway.getTaskListFromTask(task.getTaskListId());

    if (taskListOptional.isEmpty()) {
      throw new TaskListNotFoundException();
    }

    var taskList = taskListOptional.get();

    if (!taskList.getUserId().equals(userId)) {
      throw new UnauthorizedException();
    }

    var taskAlreadyExists = this.outputGateway.findByNameAndTaskList(task.getName(),
      task.getTaskListId()).isPresent();

    if (taskAlreadyExists) {
      throw new TaskAlreadyExistsException();
    }

    return outputGateway.create(task);
  }

}
