package br.com.jtech.tasklist.application.core.usecases;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.tasklist.GetTaskListByIdAdapter;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.tasklist.GetTaskListByIdUseCase;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListNotFoundException;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.GetTaskListByIdUseCaseConfig;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTaskListByIdUseCaseTest {

  @Mock
  TaskListRepository repository;

  @InjectMocks
  GetTaskListByIdAdapter adapter;

  GetTaskListByIdUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new GetTaskListByIdUseCaseConfig().useCase(this.adapter);
  }

  @Test
  @DisplayName("Should get a task list by it's id")
  void shouldGetTaskListById() {
    var id = GenId.newId();

    var user = User.builder().id(GenId.newId()).build();

    var task = Task.builder()
      .id(GenId.newId())
      .taskListId(id)
      .name("Task")
      .description("Description")
      .order(0)
      .isDone(false)
      .build();

    var list = TaskList.builder()
      .id(id)
      .userId(user.getId())
      .name("List")
      .description("Description")
      .order(0)
      .tasks(Collections.singleton(task))
      .build();

    when(this.repository.findById(UUID.fromString(id)))
      .thenReturn(Optional.of(list.toModel()));

    var result = this.useCase.getById(UUID.fromString(id), UUID.fromString(user.getId()));

    assertEquals(list, result);
    assertTrue(list.getTasks().contains(task));
  }

  @Test
  @DisplayName("Should not get a task list and throw an TaskListNotFoundException")
  void shouldThrowTaskListNotFoundException() {
    var id = GenId.newId();
    var userId = GenId.newId();

    when(this.repository.findById(any(UUID.class)))
      .thenReturn(Optional.empty());

    assertThrows(TaskListNotFoundException.class, () -> this.useCase.getById(UUID.fromString(id),
      UUID.fromString(userId)));
  }

  @Test
  @DisplayName("Should not get a task list and throw an UnauthorizedException if given userId doesn't match a list userId")
  void shouldThrowUnauthorizedException() {
    var id = GenId.newId();

    var task = Task.builder()
      .id(GenId.newId())
      .taskListId(id)
      .name("Task")
      .description("Description")
      .order(0)
      .isDone(false)
      .build();

    var list = TaskList.builder()
      .id(id)
      .userId(GenId.newId())
      .name("List")
      .description("Description")
      .order(0)
      .tasks(Collections.singleton(task))
      .build();

    when(this.repository.findById(UUID.fromString(id)))
      .thenReturn(Optional.of(list.toModel()));

    assertThrows(UnauthorizedException.class, () -> this.useCase.getById(UUID.fromString(id),
      UUID.fromString(GenId.newId())));
  }

}
