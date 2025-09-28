package br.com.jtech.tasklist.application.ports.output.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;

import java.util.Optional;
import java.util.Set;

public interface FetchUserTaskListsOutputGateway {

  Optional<User> findUserById(String id);

  Set<TaskList> fetchUserTaskLists(String userId);

}
