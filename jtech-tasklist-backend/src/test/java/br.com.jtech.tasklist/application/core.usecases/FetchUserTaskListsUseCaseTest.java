package br.com.jtech.tasklist.application.core.usecases;

import br.com.jtech.tasklist.adapters.database.FetchUserTaskListsAdapter;
import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.tasklist.FetchUserTaskListsUseCase;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListUserNotFoundException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.FetchUserTaskListsUseCaseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchUserTaskListsUseCaseTest {

  @Mock
  TaskListRepository repository;

  @Mock
  UserRepository userRepository;

  @InjectMocks
  FetchUserTaskListsAdapter adapter;

  FetchUserTaskListsUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new FetchUserTaskListsUseCaseConfig().useCase(adapter);
  }

  @Test
  @DisplayName("Should create a task list")
  void shouldCreateTaskList() {
    var user = User.builder().id(GenId.newId()).build();
    var list = TaskList.builder()
      .id(GenId.newId())
      .userId(user.getId())
      .name("List")
      .description("Description")
      .order(0)
      .build();

    when(this.userRepository.findById(any(UUID.class)))
      .thenReturn(Optional.of(user.toModel()));
    when(this.repository.findAllByUserId(eq(UUID.fromString(user.getId()))))
      .thenReturn(Collections.singleton(list.toModel()));

    var result = this.useCase.fetchUserTaskLists(UUID.fromString(user.getId()));

    assertEquals(1, result.size());
    assertTrue(result.contains(list));
  }

  @Test
  @DisplayName("Should create a task list")
  void shouldThrowTaskListUserNotFoundException() {
    var userId = GenId.newId();

    when(this.userRepository.findById(any(UUID.class)))
      .thenReturn(Optional.empty());

    assertThrows(TaskListUserNotFoundException.class, () -> this.useCase.fetchUserTaskLists(UUID.fromString(userId)));
  }
}
