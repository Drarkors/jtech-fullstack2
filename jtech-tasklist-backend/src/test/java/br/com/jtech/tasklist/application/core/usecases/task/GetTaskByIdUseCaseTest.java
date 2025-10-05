package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.adapters.database.task.GetTaskByIdAdapter;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskNotFoundException;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.task.GetTaskByIdUseCaseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTaskByIdUseCaseTest {

  @Mock
  TaskRepository repository;

  @InjectMocks
  GetTaskByIdAdapter adapter;

  GetTaskByIdUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new GetTaskByIdUseCaseConfig().useCase(adapter);
  }

  @Test
  @DisplayName("Should get a task by it's id")
  void shouldGetTaskListById() {
    var id = GenId.newId();

    var user = User.builder().id(GenId.newId()).build();

    var list = TaskList.builder()
      .id(GenId.newId())
      .userId(user.getId())
      .name("List")
      .description("Description")
      .order(0)
      .build();

    var task = Task.builder()
      .id(id)
      .taskListId(list.getId())
      .name("Task")
      .description("Description")
      .order(0)
      .isDone(false)
      .taskList(list)
      .build();

    when(this.repository.findById(eq(UUID.fromString(id))))
      .thenReturn(Optional.of(task.toModel()));

    var result = this.useCase.getById(id, user.getId());

    assertEquals(task, result);
    assertEquals(task.getTaskList(), list);
  }

  @Test
  @DisplayName("Should not be able get a task and throw an TaskNotFoundException")
  void shouldThrowTaskListNotFoundException() {
    var id = GenId.newId();
    var userId = GenId.newId();

    when(this.repository.findById(any(UUID.class)))
      .thenReturn(Optional.empty());

    assertThrows(TaskNotFoundException.class, () -> this.useCase.getById(id, userId));
  }

  @Test
  @DisplayName("Should not be able get a task and throw an UnauthorizedException if given userId doesn't match it's userId")
  void shouldThrowUnauthorizedException() {
    var id = GenId.newId();

    var list = TaskList.builder()
      .id(id)
      .userId(GenId.newId())
      .name("List")
      .description("Description")
      .order(0)
      .build();

    var task = Task.builder()
      .id(GenId.newId())
      .taskListId(id)
      .name("Task")
      .description("Description")
      .order(0)
      .isDone(false)
      .taskList(list)
      .build();

    when(this.repository.findById(eq(UUID.fromString(id))))
      .thenReturn(Optional.of(task.toModel()));

    assertThrows(UnauthorizedException.class, () -> this.useCase.getById(id, GenId.newId()));
  }
}
