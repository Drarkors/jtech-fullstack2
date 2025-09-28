package br.com.jtech.tasklist.application.core.usecases.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.adapters.database.repositories.models.TaskModel;
import br.com.jtech.tasklist.adapters.database.task.CreateTaskAdapter;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.task.exceptions.TaskAlreadyExistsException;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListNotFoundException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.task.CreateTaskUseCaseConfig;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTaskUseCaseTest {

  @Mock
  TaskRepository taskRepository;

  @Mock
  TaskListRepository taskListRepository;

  @InjectMocks
  CreateTaskAdapter adapter;

  CreateTaskUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new CreateTaskUseCaseConfig().useCase(adapter);
  }

  @Test
  @DisplayName("Should create a new task")
  void shouldCreateANewTask() {
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
      .taskListId(list.getId())
      .name("New task")
      .description("Description")
      .order(0)
      .isDone(false)
      .build();

    when(this.taskListRepository.findById(eq(UUID.fromString(list.getId())))).thenReturn(Optional.of(list.toModel()));
    when(this.taskRepository.findByNameAndTaskListId(any(), any())).thenReturn(Optional.empty());
    when(this.taskRepository.save(any(TaskModel.class))).thenAnswer((_p) -> {
      task.setId(id);
      return task.toModel();
    });

    var result = this.useCase.create(task);

    assertEquals(task.getId(), result.getId());
    assertEquals(task.getName(), result.getName());

    verify(this.taskRepository, times(1)).save(any(TaskModel.class));
  }

  @Test
  @DisplayName("Should not be able create a task if it's list isn't found")
  void shouldThrowTaskListNotFoundException() {
    var task = Task.builder()
      .taskListId(GenId.newId())
      .name("New task")
      .description("Description")
      .order(0)
      .isDone(false)
      .build();

    when(this.taskListRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

    assertThrows(TaskListNotFoundException.class, () -> this.useCase.create(task));

    verify(this.taskRepository, times(0)).save(any(TaskModel.class));
  }

  @Test
  @DisplayName("Should not be able create a task if a task with same name and list already exists")
  void shouldThrowTaskAlreadyExistsException() {
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
      .name("New task")
      .build();

    when(this.taskListRepository.findById(eq(UUID.fromString(list.getId()))))
      .thenReturn(Optional.of(list.toModel()));
    when(this.taskRepository.findByNameAndTaskListId(any(String.class), any(UUID.class)))
      .thenReturn(Optional.of(task.toModel()));

    assertThrows(TaskAlreadyExistsException.class, () -> this.useCase.create(task));

    verify(this.taskRepository, times(0)).save(any(TaskModel.class));
  }

}