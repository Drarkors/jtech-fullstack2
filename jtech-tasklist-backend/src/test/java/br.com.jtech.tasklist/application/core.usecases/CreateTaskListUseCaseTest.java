package br.com.jtech.tasklist.application.core.usecases;

import br.com.jtech.tasklist.adapters.database.CreateTaskListAdapter;
import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.models.TaskListModel;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.usecases.tasklist.CreateTaskListUseCase;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.CreateTaskListUseCaseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTaskListUseCaseTest {

  @Mock
  TaskListRepository repository;

  @InjectMocks
  CreateTaskListAdapter adapter;

  CreateTaskListUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new CreateTaskListUseCaseConfig().useCase(this.adapter);
  }

  @Test
  @DisplayName("Should create task list")
  void shouldCreateTaskList() {
    var id = GenId.newId();

    var list = TaskList.builder()
      .userId(GenId.newId())
      .name("List")
      .description("Description")
      .order(0)
      .build();

    when(this.repository.save(any(TaskListModel.class))).thenAnswer((_p) -> {
      list.setId(id.toString());
      return list.toModel();
    });

    var result = this.useCase.create(list);

    assertEquals(list.getId(), result.getId());
    assertEquals(list.getUserId(), result.getUserId());
    assertEquals(list.getName(), result.getName());
    assertEquals(list.getDescription(), result.getDescription());
    assertEquals(list.getOrder(), result.getOrder());

    verify(this.repository, times(1)).save(any(TaskListModel.class));
  }
}
