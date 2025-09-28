package br.com.jtech.tasklist.application.core.usecases;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.models.TaskListModel;
import br.com.jtech.tasklist.adapters.database.tasklist.UpdateTaskListAdapter;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.tasklist.UpdateTaskListUseCase;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListNotFoundException;
import br.com.jtech.tasklist.config.infra.exceptions.shared.UnauthorizedException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.UpdateTaskListUseCaseConfig;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateTaskListUseCaseTest {

  @Mock
  TaskListRepository repository;

  @InjectMocks
  UpdateTaskListAdapter adapter;

  UpdateTaskListUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new UpdateTaskListUseCaseConfig().useCase(this.adapter);
  }

  @Test
  @DisplayName("Should be able to update a task list name and/or order")
  void shouldBeAbleToUpdate() {
    var id = GenId.newId();

    var newOrder = 1;
    var newName = "List - Updated";

    var user = User.builder().id(GenId.newId()).build();

    var list = TaskList.builder()
      .id(id)
      .userId(user.getId())
      .name("List")
      .description("Description")
      .order(0)
      .build();

    when(this.repository.findById(UUID.fromString(id)))
      .thenReturn(Optional.of(list.toModel()));

    when(this.repository.save(any(TaskListModel.class)))
      .thenAnswer((invocation -> invocation.getArgument(0)));

    var result = this.useCase.update(id, user.getId(), newName, newOrder);

    assertEquals(newName, result.getName());
    assertEquals(newOrder, result.getOrder());

    verify(this.repository, times(1)).save(any(TaskListModel.class));
  }

  @Test
  @DisplayName("Should not be able get a task list and throw an TaskListNotFoundException")
  void shouldThrowTaskListNotFoundException() {
    var id = GenId.newId();
    var userId = GenId.newId();

    var newOrder = 1;
    var newName = "List - Updated";

    when(this.repository.findById(any(UUID.class)))
      .thenReturn(Optional.empty());

    assertThrows(TaskListNotFoundException.class, () -> this.useCase.update(id, userId, newName, newOrder));

    verify(this.repository, times(0)).save(any(TaskListModel.class));
  }

  @Test
  @DisplayName("Should not be able get a task list and throw an UnauthorizedException if given userId doesn't match a list userId")
  void shouldThrowUnauthorizedException() {
    var id = GenId.newId();
    var userId = GenId.newId();

    var newOrder = 1;
    var newName = "List - Updated";

    var list = TaskList.builder()
      .id(id)
      .userId(GenId.newId())
      .name("List")
      .description("Description")
      .order(0)
      .build();

    when(this.repository.findById(UUID.fromString(id)))
      .thenReturn(Optional.of(list.toModel()));

    assertThrows(UnauthorizedException.class, () -> this.useCase.update(id, userId, newName, newOrder));

    verify(this.repository, times(0)).save(any(TaskListModel.class));
  }

}
