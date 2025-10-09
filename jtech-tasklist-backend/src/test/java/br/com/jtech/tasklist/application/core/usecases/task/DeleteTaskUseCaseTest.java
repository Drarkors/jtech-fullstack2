package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.adapters.database.task.DeleteTaskAdapter;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskNotFoundException;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.task.DeleteTaskUseCaseConfig;
import br.com.jtech.tasklist.factories.TaskFactory;
import br.com.jtech.tasklist.factories.TaskListFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteTaskUseCaseTest {

  @Mock
  TaskRepository repository;

  @InjectMocks
  DeleteTaskAdapter adapter;

  DeleteTaskUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new DeleteTaskUseCaseConfig().useCase(adapter);
  }

  @Test
  @DisplayName("Should delete a task")
  void shouldDeleteTask() {
    var user = User.builder().id(GenId.newId()).build();

    var list = TaskListFactory.fakeTaskList(user.getId());
    var task = TaskFactory.fakeTaskList(list);

    when(this.repository.findById(eq(UUID.fromString(task.getId()))))
      .thenReturn(Optional.of(task.toModel()));

    this.useCase.delete(task.getId(), user.getId());

    verify(this.repository, times(1)).deleteById(any());
  }

  @Test
  @DisplayName("Should not be able to delete a task and throw an TaskNotFoundException")
  void shouldThrowTaskListNotFoundException() {
    var id = GenId.newId();
    var userId = GenId.newId();

    when(this.repository.findById(any(UUID.class)))
      .thenReturn(Optional.empty());

    assertThrows(TaskNotFoundException.class, () -> this.useCase.delete(id, userId));
  }

  @Test
  @DisplayName("Should not be able to delete a task and throw an UnauthorizedException if given userId doesn't match it's userId")
  void shouldThrowUnauthorizedException() {
    var user = User.builder().id(GenId.newId()).build();

    var list = TaskListFactory.fakeTaskList(user.getId());
    var task = TaskFactory.fakeTaskList(list);

    when(this.repository.findById(eq(UUID.fromString(task.getId()))))
      .thenReturn(Optional.of(task.toModel()));

    assertThrows(UnauthorizedException.class, () -> this.useCase.delete(task.getId(), GenId.newId()));
  }

}
