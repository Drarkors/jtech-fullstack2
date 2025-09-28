package br.com.jtech.tasklist.application.ports.output.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface FetchUserTaskListsOutputGateway {

  Optional<User> findUserById(UUID id);

  Set<TaskList> fetchUserTaskLists(UUID userId);

}
