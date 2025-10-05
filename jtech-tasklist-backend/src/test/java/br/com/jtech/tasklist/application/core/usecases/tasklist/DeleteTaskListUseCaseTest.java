package br.com.jtech.tasklist.application.core.usecases.tasklist;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.tasklist.DeleteTaskListAdapter;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListNotFoundException;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.tasklist.DeleteTaskListUseCaseConfig;
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
public class DeleteTaskListUseCaseTest {

  @Mock
  TaskListRepository repository;

  @InjectMocks
  DeleteTaskListAdapter adapter;

  DeleteTaskListUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new DeleteTaskListUseCaseConfig().useCase(adapter);
  }

  @Test
  @DisplayName("Should be able to delete a task list")
  void shouldDeleteTaskList() {
    var list = TaskListFactory.fakeTaskList(GenId.newId());

    when(this.repository.findById(UUID.fromString(list.getId())))
      .thenReturn(Optional.of(list.toModel()));

    this.useCase.delete(list.getId(), list.getUserId());

    verify(this.repository, times(1)).deleteById(eq(UUID.fromString(list.getId())));
  }

  @Test
  @DisplayName("Should not be able delete a task list and throw an TaskListNotFoundException")
  void shouldThrowTaskListNotFoundException() {
    var id = GenId.newId();
    var userId = GenId.newId();

    when(this.repository.findById(any(UUID.class)))
      .thenReturn(Optional.empty());

    assertThrows(TaskListNotFoundException.class, () -> this.useCase.delete(id, userId));
  }

  @Test
  @DisplayName("Should not be able delete a task list and throw an UnauthorizedException if given userId doesn't match a list userId")
  void shouldThrowUnauthorizedException() {
    var id = GenId.newId();

    var list = TaskList.builder()
      .id(id)
      .userId(GenId.newId())
      .name("List")
      .description("Description")
      .order(0)
      .build();

    when(this.repository.findById(UUID.fromString(id)))
      .thenReturn(Optional.of(list.toModel()));

    assertThrows(UnauthorizedException.class, () -> this.useCase.delete(id, GenId.newId()));
  }
}
