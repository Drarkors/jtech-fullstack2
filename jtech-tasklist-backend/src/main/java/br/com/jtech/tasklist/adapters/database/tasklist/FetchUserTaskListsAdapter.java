package br.com.jtech.tasklist.adapters.database.tasklist;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.ports.output.tasklist.FetchUserTaskListsOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * class CreateTaskListAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class FetchUserTaskListsAdapter implements FetchUserTaskListsOutputGateway {

  private final TaskListRepository repository;
  private final UserRepository userRepository;

  @Override
  public Optional<User> findUserById(String id) {
    var entity = this.userRepository.findById(UUID.fromString(id));

    return entity.map(User::of);
  }

  @Override
  public Set<TaskList> fetchUserTaskLists(String userId) {
    var entities = this.repository.findAllByUserId(UUID.fromString(userId));

    return entities.stream().map(TaskList::of).collect(Collectors.toSet());
  }
}
