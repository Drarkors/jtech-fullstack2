package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.adapters.database.task.UpdateTasksOrderAdapter;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TasksNotFoundException;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskOrderInputDTO;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.task.UpdateTasksOrderUseCaseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateTasksOrderUseCaseTest {

  @Mock
  TaskRepository repository;

  @InjectMocks
  UpdateTasksOrderAdapter adapter;

  UpdateTasksOrderUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new UpdateTasksOrderUseCaseConfig().useCase(this.adapter);
  }

  @Test
  @DisplayName("Should be able to update tasks orders")
  void shouldUpdateTask() {
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
      .id(GenId.newId())
      .taskListId(id)
      .name("Task")
      .description("Description")
      .order(0)
      .isDone(false)
      .taskList(list)
      .build();

    var dto = Collections.singletonList(new UpdateTaskOrderInputDTO(task.getId(), 1));

    when(this.repository.findAllById(any())).thenReturn(Collections.singletonList(task.toModel()));
    when(this.repository.saveAll(any())).thenAnswer((_p) -> {
      task.setOrder(dto.get(0).newOrder());
      return Collections.singletonList(task.toModel());
    });

    var result = this.useCase.updateOrder(new HashSet<>(dto), task.getTaskListId(), user.getId()).stream().toList();

    assertEquals(dto.get(0).newOrder(), result.get(0).getOrder());
  }

  @Test
  @DisplayName("Should not be able update a task and throw an TaskNotFoundException")
  void shouldThrowTaskListNotFoundException() {
    var userId = GenId.newId();

    var dto = Collections.singletonList(new UpdateTaskOrderInputDTO(GenId.newId(), 1));

    when(this.repository.findAllById(any())).thenReturn(Collections.emptyList());

    assertThrows(TasksNotFoundException.class, () -> this.useCase.updateOrder(new HashSet<>(dto), GenId.newId(), userId));
  }

  @Test
  @DisplayName("Should not be able update a task and throw an UnauthorizedException if given userId doesn't match it's userId")
  void shouldThrowUnauthorizedException() {
    var user = User.builder().id(GenId.newId()).build();

    var list = TaskList.builder()
      .id(GenId.newId())
      .userId(user.getId())
      .name("List")
      .description("Description")
      .order(0)
      .build();

    var task = Task.builder()
      .id(GenId.newId())
      .taskListId(list.getId())
      .name("Task")
      .description("Description")
      .order(0)
      .isDone(false)
      .taskList(list)
      .build();

    var dto = Collections.singletonList(new UpdateTaskOrderInputDTO(task.getId(), 1));

    when(this.repository.findAllById(any())).thenReturn(Collections.singletonList(task.toModel()));

    assertThrows(UnauthorizedException.class, () ->
      this.useCase.updateOrder(new HashSet<>(dto), list.getId(), GenId.newId()));
  }

}
