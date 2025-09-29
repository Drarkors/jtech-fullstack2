package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.adapters.database.repositories.models.TaskModel;
import br.com.jtech.tasklist.adapters.database.task.UpdateTaskAdapter;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskNotFoundException;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskInputDTO;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.task.UpdateTaskUseCaseConfig;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateTaskUseCaseTest {

  @Mock
  TaskRepository repository;

  @InjectMocks
  UpdateTaskAdapter adapter;

  UpdateTaskUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new UpdateTaskUseCaseConfig().useCase(this.adapter);
  }

  @Test
  @DisplayName("Should be able to update a task")
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

    var dto = new UpdateTaskInputDTO("Task - Updated", "Description - Updated", 1);

    when(this.repository.findById(UUID.fromString(id)))
      .thenReturn(Optional.of(task.toModel()));
    when(this.repository.save(any(TaskModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

    var result = this.useCase.update(dto, id, user.getId());

    assertEquals(dto.name(), result.getName());
    assertEquals(dto.description(), result.getDescription());
    assertEquals(dto.order(), result.getOrder());
  }

  @Test
  @DisplayName("Should not be able update a task and throw an TaskNotFoundException")
  void shouldThrowTaskListNotFoundException() {
    var id = GenId.newId();
    var userId = GenId.newId();

    var dto = new UpdateTaskInputDTO("Task - Updated", "Description - Updated", 1);

    when(this.repository.findById(any(UUID.class)))
      .thenReturn(Optional.empty());

    assertThrows(TaskNotFoundException.class, () -> this.useCase.update(dto, id, userId));
  }

  @Test
  @DisplayName("Should not be able update a task and throw an UnauthorizedException if given userId doesn't match it's userId")
  void shouldThrowUnauthorizedException() {
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

    var dto = new UpdateTaskInputDTO("Task - Updated", "Description - Updated", 1);

    when(this.repository.findById(UUID.fromString(id)))
      .thenReturn(Optional.of(task.toModel()));

    assertThrows(UnauthorizedException.class, () -> this.useCase.update(dto, id, GenId.newId()));
  }

}
